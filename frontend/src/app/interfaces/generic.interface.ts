export interface GenericApiResponse<Data> {
  status: number;
  timestamp: string;
  message?: string;
  error?: string;
  data?: Data;
}
