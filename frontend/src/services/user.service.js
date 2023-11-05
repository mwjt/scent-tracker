import axios from "axios";
import authHeader from "./auth-header";

class UserService {
  getPublicContent() {
    return axios.get('/api/v1/all');
  }

  getUserBoard() {
    return axios.get('/api/v1/user', { headers: authHeader() })
  }

  getModeratorBoard() {
    return axios.get('/api/v1/mod', { headers: authHeader() })
  }

  getAdminBoard() {
    return axios.get('/api/v1/admin', { headers: authHeader() })
  }
}

export default new UserService();