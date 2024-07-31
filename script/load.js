import http from 'k6/http';
import { check, sleep } from 'k6';
import { Counter } from 'k6/metrics';
import { htmlReport } from './report.js';

export const requests = new Counter('http_reqs');

export const options = {
    stages: [
        { duration: '5s', target: 20 },
    ],
};

export default function () {
    const res = http.get('http://localhost:8080/posts?page=0&size=10');
    check(res, { 'status was 200': (r) => r.status === 200 });
    sleep(1);
}

export function handleSummary(data) {
    return {
        'summary.html': htmlReport(data),
    };
}