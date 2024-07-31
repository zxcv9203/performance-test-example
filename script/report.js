export function htmlReport(data) {
    const timestamp = new Date().toISOString();

    // HTTP 요청 응답 시간 데이터 정리
    const httpReqDuration = data.metrics.http_req_duration ? data.metrics.http_req_duration.values : {};
    const httpReqDurationStats = [
        { label: '최대 응답 시간', value: httpReqDuration.max },
        { label: '응답 시간의 90% 이하', value: httpReqDuration['p(90)'] },
        { label: '응답 시간의 95% 이하', value: httpReqDuration['p(95)'] },
        { label: '평균 응답 시간', value: httpReqDuration.avg },
        { label: '최소 응답 시간', value: httpReqDuration.min },
        { label: '중간 응답 시간', value: httpReqDuration.med },
    ];

    // 테스트 정보 데이터 정리
    const vusMax = data.metrics.vus_max ? data.metrics.vus_max.values.max : 'N/A';

    return `
        <html>
        <head>
            <meta charset="UTF-8">
            <title>k6 부하 테스트 요약</title>
            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
            <style>
                body { font-family: Arial, sans-serif; }
                .chart-container { width: 80%; margin: auto; }
            </style>
        </head>
        <body>
            <h1>k6 부하 테스트 요약</h1>
            <p><strong>타임스탬프:</strong> ${timestamp}</p>
            <p><strong>가상 사용자 수 (최대):</strong> ${vusMax}</p>
            <h2>HTTP 요청 응답 시간</h2>
            <div class="chart-container">
                <canvas id="httpReqDurationChart"></canvas>
            </div>
            <script>
                var ctx = document.getElementById('httpReqDurationChart').getContext('2d');
                var chart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: ${JSON.stringify(httpReqDurationStats.map(stat => stat.label))},
                        datasets: [{
                            label: 'HTTP 요청 응답 시간 (ms)',
                            data: ${JSON.stringify(httpReqDurationStats.map(stat => stat.value))},
                            borderColor: 'rgba(75, 192, 192, 1)',
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            fill: true
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            </script>
        </body>
        </html>`;
}
