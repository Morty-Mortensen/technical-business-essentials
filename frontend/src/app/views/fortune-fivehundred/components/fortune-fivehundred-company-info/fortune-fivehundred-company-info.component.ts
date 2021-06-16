import {Component, OnInit} from '@angular/core';
import DateRange from "../../../../models/date-range";
import {FortuneFivehundredService} from "../../../../services/fortune-fivehundred.service";

@Component({
  selector: 'app-fortune-fivehundred-company-info',
  templateUrl: './fortune-fivehundred-company-info.component.html',
  styleUrls: ['./fortune-fivehundred-company-info.component.scss']
})
export class FortuneFivehundredCompanyInfoComponent implements OnInit {

  public dateRange: DateRange | undefined;

  constructor(private fortuneFivehundredService: FortuneFivehundredService) {
  }

  ngOnInit(): void {
  }

  public submit(): void {
    if (this.dateRange !== undefined) {
      this.fortuneFivehundredService.getCompanies(this.dateRange)
        .subscribe((response) => {
          console.log(response);
        })
    }
  }

}
