import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class FortuneFivehundredService {

  // private baseUrl = 'http://localhost:8080/fortunefivehundred/companies'
  private baseUrl = 'http://ec2-user@ec2-54-219-136-197.us-west-1.compute.amazonaws.com:8080/fortunefivehundred/companies'

  constructor(private http: HttpClient) {
  }

  public getCompanies(url: string): Observable<any> {
    return this.http.get<any>(url)
      .pipe(
        map(response => {
          return response;
        })
      );
  }

  public getCompanyUrls(years: string[]) {
    return this.http.post<any>(this.baseUrl, {
      years: years
    })
      .pipe(
        map(response => {
          return response;
        })
      );
  }

  // ------- Columns --------
  // [
  //   {
  //     "key":"field_meta",
  //     "sortable":[
  //       {
  //         "franchiseId":3040727,
  //         "title":"Rank",
  //         "description":"Companies are ranked by total revenues for their respective fiscal years.",
  //         "type":"Number",
  //         "saveIn":"ranking",
  //         "importField":"rank",
  //         "sortable":"sortable",
  //         "order":"asc"
  //       },
  //       {
  //         "franchiseId":3040727,
  //         "title":"Revenues ($M)",
  //         "altTitle":"Revenues ($M)",
  //         "description":"Companies are ranked by total revenues for their respective fiscal years.",
  //         "type":"Money",
  //         "saveIn":"post_meta",
  //         "importField":"f500_revenues",
  //         "sortable":"sortable",
  //         "order":"desc"
  //       },

// {
//   "key":"items",
//   "items":[
//     {
//       "fields":[
//         {
//           "key":"rank",
//           "value":"991"
//         },
//         {
//           "key":"f500_revenues",
//           "value":"1874.1"
//         },
//         {
//           "key":"revchange",
//           "value":"3.5"
//         },
//         {
//           "key":"f500_profits",
//           "value":"-26.3"
//         },
//         {
//           "key":"prftchange",
//           "value":"-105.2"
//         },
//         {
//           "key":"assets",
//           "value":"2993.3"
//         },
//         {
//           "key":"f500_mktval",
//           "value":"1354.7"
//         },
//         {
//           "key":"rankchange1000",
//           "value":""
//         },
//         {
//           "key":"f500_employees",
//           "value":"11900"
//         },
//         {
//           "key":"rankchange",
//           "value":""
//         },
//         {
//           "key":"measure-up-rank",
//           "value":""
//         },
//         {
//           "key":"name",
//           "value":"Harsco"
//         },
//         {
//           "key":"sector",
//           "value":"Business Services"
//         },
//         {
//           "key":"f500_industry",
//           "value":"Waste Management"
//         },
//         {
//           "key":"hqcity",
//           "value":"Camp Hill"
//         },
//         {
//           "key":"hqstate",
//           "value":"PA"
//         },
//         {
//           "key":"rankgain",
//           "value":""
//         },
//         {
//           "key":"rankdrop",
//           "value":""
//         },
//         {
//           "key":"newcomer",
//           "value":"no"
//         },
//         {
//           "key":"profitable",
//           "value":"no"
//         },
//         {
//           "key":"ceofounder",
//           "value":"no"
//         },
//         {
//           "key":"ceowoman",
//           "value":"no"
//         },
//         {
//           "key":"jobgrowth",
//           "value":"yes"
//         },
//         {
//           "key":"global500-y-n",
//           "value":"no"
//         },
//         {
//           "key":"best-companies-y-n",
//           "value":"no"
//         },
//         {
//           "key":"measure-up-y-n",
//           "value":"no"
//         },
//         {
//           "fieldMeta":{
//             "title":"Company",
//             "type":"Text",
//             "importField":"title",
//             "order":"desc"
//           },
//           "key":"title",
//           "value":"Harsco"
//         }
//       ],
//       "permalink":"https:\/\/fortune.com\/company\/harsco\/fortune500\/"
//     },
//     {
//       "fields":[
//         {
//           "key":"rank",
//           "value":"992"
//         },


}
