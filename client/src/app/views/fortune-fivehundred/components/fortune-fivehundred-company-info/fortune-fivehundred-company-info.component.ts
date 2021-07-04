import {Component, OnInit, AfterViewInit, ViewChild} from '@angular/core';
import DateRange from "../../../../models/date-range";
import {FortuneFivehundredService} from "../../../../services/fortune-fivehundred.service";
import {DateFormatterService} from "../../../../services/date-formatter.service";
import {
  FortuneFiveHundredCompanyInfoColumn,
} from "../../../../models/fortune-fivehundred-company-info";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";
import {CurrencyPipe, DecimalPipe, TitleCasePipe} from "@angular/common";
import {isNumeric} from "rxjs/internal-compatibility";
import {ExcelService} from "../../../../services/excel.service";
import * as uuid from 'uuid';
import {LoadingService} from "../../../../services/loading.service";

const BEGIN_YEAR: number = 1996;

@Component({
  selector: 'app-fortune-fivehundred-company-info',
  templateUrl: './fortune-fivehundred-company-info.component.html',
  styleUrls: ['./fortune-fivehundred-company-info.component.scss']
})
export class FortuneFivehundredCompanyInfoComponent implements OnInit, AfterViewInit {

  public dateRange: DateRange | undefined;
  public companies: Record<string, any[]> = {};
  public displayYear: string = '';
  public allYears: string[] = [];
  public displayCompany: any[] = [];
  public displayColumns: string[] = []
  public displayKeyColumns: string[] = []
  public columns: Record<string, string[]> = {};
  public loading: boolean = false;
  public currYear: number = new Date().getFullYear();

  constructor(private fortuneFivehundredService: FortuneFivehundredService,
              private decimalPipe: DecimalPipe,
              private currencyPipe: CurrencyPipe,
              private titleCasePipe: TitleCasePipe,
              private excelService: ExcelService,
              private loadingService: LoadingService) {
  }

  public dataSource = new MatTableDataSource<any>([]);

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    // Initialize paginator.
    if (this.paginator !== undefined) {
      this.dataSource.paginator = this.paginator;
    }
  }

  public submit(): void {
    if (this.dateRange !== undefined) {
      const startYear = DateFormatterService.dateToYear(this.dateRange.start);
      const endYear = DateFormatterService.dateToYear(this.dateRange.end);

      if (startYear < BEGIN_YEAR) {
        alert("Please select a start year after " + (BEGIN_YEAR - 1));
        return;
      }

      if (endYear > this.currYear) {
        alert("Please select a end year before or including this year (" + this.currYear + ").");
        return;
      }

      this.loadingService.start();
      this.displayYear = String(endYear);

      let years: string[] = [];
      for (let year = startYear; year <= endYear; year++) {
        years.push(String(year));
      }

      this.loading = true;
      this.fortuneFivehundredService.getCompanyUrls(years)
        .subscribe(urlByYear => {
          this.allYears = Object.keys(urlByYear);
          this.allYears.sort((a, b) => Number(b) - Number(a));
          this.allYears.forEach((year) => {
            this.fortuneFivehundredService.getCompanies(urlByYear[year])
              .subscribe(company => {

                if (this.displayYear === year) {
                  this.processCompany(company);
                }
                this.companies[year] = company;
                this.columns[year] = this.processColumns(company);
              })
          })
          this.loadingService.stop();
        })
    } else {
      alert("Please select a date range :)")
    }
  }

  private processColumns(company: any) {
    let columns: any[] = []

    const firstColumns = company[0].sortable;
    firstColumns.forEach((column: FortuneFiveHundredCompanyInfoColumn) => {
      columns.push(column.title);
    })
    const secondColumns = company[0].filterable;
    secondColumns.forEach((column: FortuneFiveHundredCompanyInfoColumn) => {
      columns.push(column.title);
    })

    return columns;
  }

  public processCompany(company: any) {
    this.displayCompany = [];
    this.displayColumns = [];

    // Get Columns
    if (this.displayColumns.length <= 0) {
      this.displayColumns = this.processColumns(company);
    }

    // Companies info
    const companies = company[1].items;
    companies.forEach((companyInfo: any) => {
      const rowData = this.reduceToRowData(companyInfo.fields);

      // Get columns
      if (this.displayCompany.length <= 0) {
        this.displayKeyColumns = Object.keys(rowData);
      }
      this.displayCompany.push(rowData);
    })

    // Sort company info by rank
    this.displayCompany.sort((a, b) => Number(a.rank) - Number(b.rank));


    // Display company info.
    if (this.paginator !== undefined) {
      this.dataSource = new MatTableDataSource<any>(this.displayCompany);
      this.dataSource.paginator = this.paginator;
    }
  }

  public yearSelected(year: string): void {
    this.displayYear = year;
    this.processCompany(this.companies[year]);
  }

  public exportAllData(): void {
    this.allYears.forEach((year) => {
      this.exportData(this.companies[year], year);
    })
  }

  public exportData(companies: any[] = this.companies[this.displayYear], year: string = this.displayYear): void {

    // Format Data.
    let dataToExport: any[] = [];
    companies[1].items.forEach((companyInfo: any) => {
      dataToExport.push(this.reduceToRowData(companyInfo.fields, true, this.columns[year]));
    });

    dataToExport.sort((a, b) => Number(a.Rank) - Number(b.Rank));

    // Export Data.
    this.excelService.exportAsExcelFile(dataToExport, year + '_fortune_fivehundred_' + uuid.v4());
  }

  private reduceToRowData(fields: any[], prettier = false, columns: string[] = []) {
    return fields.reduce((allFields: any, field: any, index: number) => {
      if (prettier) {
        allFields[columns[index] || this.titleCasePipe.transform(field.key)] = this.formatCell(field.value, columns[index] || '');
      } else {
        allFields[field.key] = field.value;
      }

      return allFields;
    }, {});
  }

  public formatCell(cell: string, columnName: string): string {
    if (cell === '') {
      cell = '0'; // Default to zero.
    }

    const isPercent = columnName.toLowerCase().includes("percent");
    const isRank = columnName.toLowerCase().includes("rank");
    if (isPercent) {
      // console.log(columnName)
      // console.log(cell)
      cell = this.decimalPipe.transform(cell) || '';
      return cell + '%';
    } else if (isNumeric(cell) && !isRank) {
      return this.currencyPipe.transform(cell) || '';
    } else {
      return cell; // No formatting necessary.
    }
  }

  public formatHeaderCell(headerCell: string): string {
    return this.titleCasePipe.transform(headerCell);
  }
}
