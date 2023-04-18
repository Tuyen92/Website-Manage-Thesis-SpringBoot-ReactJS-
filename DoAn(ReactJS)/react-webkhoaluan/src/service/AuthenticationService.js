import axios from "axios";

const API_URL = "http://localhost:8080/";

class AuthService {
    dangNhap(username, password) {
        return axios
          .post(API_URL + "DangNhap", {
            username,
            password
          })
          .then(response => {
            if (response.data.accessToken) {
              localStorage.setItem("nguoidung", JSON.stringify(response.data));
            }
    
            return response.data;
          });
      }
    
      dangxuat() {
        localStorage.removeItem("nguoidung");
      }
    
      
    
      getCurrentUser() {
        return JSON.parse(localStorage.getItem('nguoidung'));;
      }
}

export default new AuthService();