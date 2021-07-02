// [0].sortable (11) 0 -> 10
// [0].filterable (15) 11 -> 26
export interface FortuneFiveHundredCompanyInfoColumn {
  franchiseId: number //1233333
  importField: string //measure-up-rank
  order: string // asc
  saveIn: string // post_meta
  sortable: string // sortable
  title: string // Measure Up Rank
  type: string // Number
}

// [1][0].fields[0..].value
// [1][1].fields[0..].value
// 0 -> 26 (Don't need 26)
export interface FortuneFiveHundredCompanyInfoRow {
  rank: string;
  revenues: string; // ($M)
  revenuePercentChange: string;
  profits: string // ($M)
  profitsPercentChange: string;
  assets: string // ($M)
  marketValue: string // As of March 31, 2021 ($M)
  changeInRank1000: string;
  employees: string;
  changeInRank500: string;
  measureUpRank: string;
  company: string;
  sector: string;
  industry: string;
  headquartersCity: string;
  headquartersState: string;
  gainedInRank: string;
  droppedInRank: string;
  newcomerToTheFortune500: string;
  profitable: string;
  founderIsCEO: string;
  femaleCEO: string;
  growthInJobs: string;
  global500: string;
  bestCompanies: string;
  measureUp: string;
}
