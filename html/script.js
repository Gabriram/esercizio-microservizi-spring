
async function loadGarages() {
    const res = await fetch("http://localhost:8083/api/garages");
    const data = await res.json();
    document.getElementById("output").textContent =
        JSON.stringify(data, null, 2);



}

async function loadCars() {
    const res = await fetch("http://localhost:8082/api/cars");
    const data = await res.json();
    document.getElementById("output").textContent =
        JSON.stringify(data, null, 2);
}


async function loadBikes() {
    const res = await fetch("http://localhost:8081/api/bikes");
    const data = await res.json();
    document.getElementById("output").textContent =
        JSON.stringify(data, null, 2);
}

async function loadGarageAndVehicleData() { }

