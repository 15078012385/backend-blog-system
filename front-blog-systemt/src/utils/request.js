import axios from "axios";

const request = axios.create({
    baseURL: "http://111.230.56.200:9999/",
    timeout: 5000,//请求超时时间
});

// 请求拦截
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    // 所有请求带上token
    config.headers['token'] = localStorage.getItem('token');
    return config;
}, error => {
    return Promise.reject(error);
});
// 响应拦截
request.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        return Promise.reject(error);
    }
);

export default request