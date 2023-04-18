export default function dataService() {
    const user = JSON.parse(localStorage.getItem('nguoidung'));
  
    if (user && user.accessToken) {
      return { Authorization: 'Bearer ' + user.accessToken };
    } else {
      return {};
    }
  }