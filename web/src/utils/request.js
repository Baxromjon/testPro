import {BASEURL, TOKEN} from "./constant";
import axios from "axios"

export default (params) => {
    let url = BASEURL + params.url;
    let method = params.method;
    let data = params.data;

    return axios({
        url,
        method,
        data,
        headers: {
            "Authorization": "Bearer " + TOKEN

        }
        // headers: {
        //     "Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNjI5MzkwNTM1LCJleHAiOjE2Mjk5OTUzMzV9.C5ESfHNg7ZTZqCXA07D1cHj-1Woxe0_FOS7JSlF6sX6vAuD7ZA2S-9VgmkJHlWJ5iIeXNYaJp4EMfVtS0pRYeQ"
        //
        // }
    })
}