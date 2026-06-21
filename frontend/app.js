const API = "http://localhost:8080/api/simulation";

// ---------------- STATE UI ----------------

async function fetchState() {
    const res = await fetch(`${API}/state`);
    return res.json();
}

async function fetchHistory() {
    const res = await fetch(`${API}/history`);
    return res.json();
}

async function updateUI() {
    const state = await fetchState();

    document.getElementById("time").innerText = state.time;
    document.getElementById("load").innerText = state.netLoadKw.toFixed(2);
    document.getElementById("soc").innerText = state.batterySocKwh.toFixed(2);
    document.getElementById("power").innerText = state.batteryPowerKw.toFixed(2);
}

// ---------------- CONTROLS ----------------

async function startSim() {
    await fetch(`${API}/start`, { method: "POST" });
}

async function stopSim() {
    await fetch(`${API}/stop`, { method: "POST" });
}

// ---------------- CHART ----------------

let chart;

async function loadChart() {
    const data = await fetchHistory();

    const labels = data.map(d => d.time);
    const values = data.map(d => d.value);

    if (!chart) {
        const ctx = document.getElementById("chart").getContext("2d");

        chart = new Chart(ctx, {
            type: "line",
            data: {
                labels: labels,
                datasets: [{
                    label: "Net Load (kW)",
                    data: values,
                    borderColor: "blue",
                    fill: false,
                    tension: 0.2
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        display: true
                    }
                }
            }
        });
    } else {
        chart.data.labels = labels;
        chart.data.datasets[0].data = values;
        chart.update();
    }
}

// ---------------- AUTO REFRESH ----------------

setInterval(updateUI, 1000);
setInterval(loadChart, 5000);