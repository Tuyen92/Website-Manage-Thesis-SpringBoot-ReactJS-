import axios from "axios";
import dataService from "./DataService";

class UserService {
    getPublicContent() {
      return axios.get(API_URL + 'all');
    }
  
    getGiangvienBoard() {
      return axios.get(API_URL + 'giangvien', { headers: dataService() });
    }

    getSinhvienBoard() {
        return axios.get(API_URL + 'sinhvien', { headers: dataService() });
      }
  
    getQuantriBoard() {
      return axios.get(API_URL + 'quantri', { headers:dataService() });
    }
  
    getGiaovuBoard() {
      return axios.get(API_URL + 'giaovu', { headers: dataService() });
    }
  }
  
  export default new UserService();