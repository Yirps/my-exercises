// Fetch API Data
async function fetchDataUser(id) {
    try {
        const response = await fetch(`http://localhost:8080/api/user/${id}`);
        if (!response.ok) throw new Error('Failed to fetch data');
        console.log(response);
        
        const data = await response.json();
        console.log(data); // Use the data in your UI
    } catch (error) {
        console.error('Error:', error);
    }
}
// Call the fetch function
fetchDataUser();

// Fetch API Data
async function fetchDataParticipants() {
    try {
        const response = await fetch('http://localhost:8080/api/activity-participants');
        if (!response.ok) throw new Error('Failed to fetch data');
        
        const data = await response.json();
        console.log(data); // Use the data in your UI
    } catch (error) {
        console.error('Error:', error);
    }
}

// Call the fetch function
fetchDataParticipants();

const API_ACTIVITIES_URL = "http://localhost:8080/api/planned-activities";

// Fetch API Data
async function fetchDataActivity() {
    try {
        const response = await fetch(API_ACTIVITIES_URL);
        if (!response.ok) throw new Error('Failed to fetch data');
        
        const data = await response.json();
        console.log(data); // Use the data in your UI
    } catch (error) {
        console.error('Error:', error);
    }
}
// Call the fetch function
fetchDataActivity();

async function createActivity(newActivity) {
    try {
        const response = await fetch(API_ACTIVITIES_URL + '/add', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newActivity),
        });
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const createdActivity = await response.json();
        console.log("Activity created:", createdActivity);

        const table = document.querySelector(".activities-list table tbody");
        const newRow = table.insertRow();

        newRow.innerHTML = `    
            <td>${createdActivity.type}</td>
            <td>${createdActivity.date}</td>    
            <td>${createdActivity.time}</td>    
            <td>${createdActivity.location}</td>    
            <td>${createdActivity.peopleNeeded}</td>
            <td>
                <button class="contact-btn">Contact People</button> 
                <button class="delete-btn">Delete</button>  
            </td>    
        `;

        const deleteButton = newRow.querySelector(".delete-btn");
        deleteButton.addEventListener("click", function () {
            if (confirm("Are you sure you want to delete this activity?")) {
                newRow.remove();
            }
        });
    
    } catch (error) {
        console.error("Error creating activity:", error);
    }
}



// Evento de delete de atividades
document.addEventListener("DOMContentLoaded", function () {
    const deleteButtons = document.querySelectorAll(".delete-btn");

    deleteButtons.forEach(button => {
        button.addEventListener("click", function () {
            const row = button.closest("tr");
            if (confirm("Are you sure you want to delete this activity?")) {
                row.remove();
            }
        });
    });
});

// Modal elements
const openFormBtn = document.getElementById("openFormBtn");
const closeFormBtn = document.getElementById("closeFormBtn");
const activityFormModal = document.getElementById("activityFormModal");
const newActivityForm = document.getElementById("newActivityForm");

// Abrir modal
openFormBtn.addEventListener("click", () => {
    activityFormModal.style.display = "block";
});

// Fechar modal
closeFormBtn.addEventListener("click", () => {
    activityFormModal.style.display = "none";
});

// Submissão do formulário
newActivityForm.addEventListener("submit", (e) => {
    e.preventDefault();

    // Captura os valores do formulário
    const type = document.getElementById("activityType").value;
    const date = document.getElementById("activityDate").value;
    const time = document.getElementById("activityTime").value;
    const location = document.getElementById("activityLocation").value;
    const peopleNeeded = document.getElementById("activityPeople").value;

    const newActivity = {
        type: type, 
        date: date, 
        time: time, 
        location: location, 
        peopleNeeded: peopleNeeded    
    };  

    createActivity(newActivity);
    // Fechar modal e limpar formulário
    activityFormModal.style.display = "none";
    newActivityForm.reset();
});
