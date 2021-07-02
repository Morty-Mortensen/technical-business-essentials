import {Component, OnInit, ViewChild} from '@angular/core';
import DateRange from "../../../../models/date-range";
import {FortuneFivehundredService} from "../../../../services/fortune-fivehundred.service";
import {DateFormatterService} from "../../../../services/date-formatter.service";
import {
  FortuneFiveHundredCompanyInfoColumn,
  FortuneFiveHundredCompanyInfoRow
} from "../../../../models/fortune-fivehundred-company-info";
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-fortune-fivehundred-company-info',
  templateUrl: './fortune-fivehundred-company-info.component.html',
  styleUrls: ['./fortune-fivehundred-company-info.component.scss']
})
export class FortuneFivehundredCompanyInfoComponent implements OnInit {

  public dateRange: DateRange | undefined;
  public companies: Record<string, any[]> = {};
  public displayCompany: FortuneFiveHundredCompanyInfoRow[] = [];
  public displayColumns: string[] = [
    "Rank",
    "Revenues ($M)",
    "Revenue Percent Change",
    "Profits ($M)",
    "Profits Percent Change",
    "Assets ($M)",
    "Market Value — as of March 31, 2021 ($M)",
    "Change in Rank (Full 1000)",
    "Employees",
    "Change in Rank (500 only)",
    "Measure Up Rank",
    "Company",
    "Sector",
    "Industry",
    "Headquarters City",
    "Headquarters State",
    "Gained in Rank",
    "Dropped in Rank",
    "Newcomer to the Fortune 500",
    "Profitable",
    "Founder is CEO",
    "Female CEO",
    "Growth in Jobs",
    "Global 500",
    "Best Companies",
    "Measure Up"
  ];
  public loading: boolean = false;

  constructor(private fortuneFivehundredService: FortuneFivehundredService) {
  }

  public testData: FortuneFiveHundredCompanyInfoRow[] = [{
    rank: "1",
    revenues: "559151", // ($M)
    revenuePercentChange: "9.2",
    profits: "25242452", // ($M)
    profitsPercentChange: "2.3",
    assets: "24524",
    marketValue: "2352556", // As of March 31, 2021 ($M)
    changeInRank1000: "20",
    employees: "23000000",
    changeInRank500: "10",
    measureUpRank: "20",
    company: "Walmart",
    sector: "Retailing",
    industry: "General Merchandisers",
    headquartersCity: "Bentonville",
    headquartersState: "AR",
    gainedInRank: "no",
    droppedInRank: "no",
    newcomerToTheFortune500: "no",
    profitable: "yes",
    founderIsCEO: "no",
    femaleCEO: "no",
    growthInJobs: "yes",
    global500: "yes",
    bestCompanies: "yes",
    measureUp: "yes",
  }];
  public dataSource = new MatTableDataSource<FortuneFiveHundredCompanyInfoRow>(this.testData);

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  ngAfterViewInit() {
    if (this.paginator !== undefined) {
      console.log("Paginator here!")

    }

  }

  ngOnInit(): void {
  }

  public submit(): void {
    if (this.dateRange !== undefined) {

      const startYear = DateFormatterService.dateToYear(this.dateRange.start);
      const endYear = DateFormatterService.dateToYear(this.dateRange.end);

      let years: string[] = [];
      for (let year = startYear; year <= endYear; year++) {
        years.push(String(year));
      }

      this.loading = true;
      this.fortuneFivehundredService.getCompanyUrls(years)
        .subscribe(urlByYear => {
          years = Object.keys(urlByYear);
          years.forEach((year) => {
            this.fortuneFivehundredService.getCompanies(urlByYear[year])
              .subscribe(company => {

                if (Object.keys(this.companies).length <= 0) {
                  this.processCompany(company);
                }

                this.companies[year] = company;
              })
          })
        })
    } else {
      alert("Please select a date range :)")
    }
  }

  public processCompany(company: any) {
    this.displayCompany = [];

    // Get Columns
    // if (this.displayColumns.length <= 0) {
    //   const firstColumns = company[0].sortable;
    //   firstColumns.forEach((column: FortuneFiveHundredCompanyInfoColumn) => {
    //     this.displayColumns.push(column.title);
    //   })
    //   const secondColumns = company[0].filterable;
    //   secondColumns.forEach((column: FortuneFiveHundredCompanyInfoColumn) => {
    //     this.displayColumns.push(column.title);
    //   })
    // }
    //
    // console.log(this.displayColumns)

    // Companies info
    const companies = company[1].items;
    companies.forEach((companyInfo: any) => {
      this.displayCompany.push({
        rank: companyInfo.fields[0].value,
        revenues: companyInfo.fields[1].value,
        revenuePercentChange: companyInfo.fields[2].value,
        profits: companyInfo.fields[3].value,
        profitsPercentChange: companyInfo.fields[4].value,
        assets: companyInfo.fields[5].value,
        marketValue: companyInfo.fields[6].value,
        changeInRank1000: companyInfo.fields[7].value,
        employees: companyInfo.fields[8].value,
        changeInRank500: companyInfo.fields[9].value,
        measureUpRank: companyInfo.fields[10].value,
        company: companyInfo.fields[11].value,
        sector: companyInfo.fields[12].value,
        industry: companyInfo.fields[13].value,
        headquartersCity: companyInfo.fields[14].value,
        headquartersState: companyInfo.fields[15].value,
        gainedInRank: companyInfo.fields[16].value,
        droppedInRank: companyInfo.fields[17].value,
        newcomerToTheFortune500: companyInfo.fields[18].value,
        profitable: companyInfo.fields[19].value,
        founderIsCEO: companyInfo.fields[20].value,
        femaleCEO: companyInfo.fields[21].value,
        growthInJobs: companyInfo.fields[22].value,
        global500: companyInfo.fields[23].value,
        bestCompanies: companyInfo.fields[24].value,
        measureUp: companyInfo.fields[25].value
      } as FortuneFiveHundredCompanyInfoRow)
    })

    this.displayCompany.sort((a, b) => Number(a.rank) - parseFloat(b.rank));

    if (this.paginator !== undefined) {
      this.dataSource = new MatTableDataSource<FortuneFiveHundredCompanyInfoRow>(this.displayCompany);
      this.dataSource.paginator = this.paginator;
    }

    console.log(this.displayCompany);
  }
}
