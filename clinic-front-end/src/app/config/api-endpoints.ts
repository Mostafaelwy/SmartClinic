import { environment } from "../../env/enviroment";

const BASE_URL = environment.apiBaseUrl;

const API_ENDPOINTS_RAW = {
    AUTH: {
      _prefix: 'auth',
      LOGIN: '/login',
    },
    DOCTOR:{
      _prefix: 'smart/doctor',
      STAISTICS:'/me/statistics',
      RESERVATIONS:'/me/reservations',
      RESERVATION:'/reservation'
    }
    // Add other endpoint groups as needed
  };
  

function wrapNode(node: any, parentPrefix: string = ''): any {
  const prefix = node._prefix ? `${parentPrefix}/${node._prefix}` : parentPrefix;

  const endpoints = node.endpoints || node;

  return new Proxy(endpoints, {
    get(target, key) {
      const value = target[key];

      if (!value) return undefined;

      if (typeof value === 'string') {
        // It's an endpoint string
        console.log("returrrrning", `${BASE_URL}${prefix}${value}`)
        return `${BASE_URL}${prefix}${value}`;
      } else if (typeof value === 'object') {
        // It's a nested group
        return wrapNode(value, prefix);
      }

      return undefined;
    }
  });
}
export const API_ENDPOINTS = wrapNode(API_ENDPOINTS_RAW);
