function getData(url) {
    fetch(url)
        .then((response) => response.json())
        .then((data) => populateTable(data))
        .catch((error) => console.error(error));
}

getData('http://localhost:8080/javabank5/api/customer');

async function populateTable(data) {
    const tableBody = document.getElementById('customers-table').getElementsByTagName('tbody')[0];  
      
    data.forEach((customer) => {
        const row = tableBody.insertRow();
        
        const firstNameCell = row.insertCell();
        firstNameCell.innerHTML = customer.firstName;

        const lastNameCell = row.insertCell();
        lastNameCell.innerHTML = customer.lastName;

        const emailCell = row.insertCell();
        emailCell.innerHTML = customer.email;

        const phoneCell = row.insertCell();
        phoneCell.innerHTML = customer.phone;

        const editButton = row.insertCell();
        editButton.innerHTML = '<button type="submit" class="btn btns-success" id="update-button">Edit</button>';

        editButton.addEventListener('click', () => {
            fetchUser(customer.id).then((data) => {
                const form = document.getElementById("update-button");
            })
           
        });

        const deleteButton = row.insertCell();
        deleteButton.innerHTML = '<button type="submit" class="btn btn-danger">Delete</button>';

        editButton.addEventListener('click', () => {
            deleteUser(customer.id);
        });
    });

}

async function deleteUser(id) {
    const api = "http://localhost:8080/javabank5/api/customer";
    const response = await fetch(`${api}/${id}`, {
      method: 'DELETE'
    });
    await getData(url);
  }


async function fetchUser(id) {
    const api = "http://localhost:8080/javabank5/api/customer";
    const response = await fetch(`${api}/${id}`);
    const user = await response.json();
    const form = document.getElementById("update-button");
    return {
      firstName: form.firstName.value = user.firstName,
      lastName: form.lastName.value = user.lastName,
      email: form.email.value = user.email,
      phone: form.phone.value = user.phone,
    };
  }
  
  
  async function updateUser(id) {
    const api = "http://localhost:8080/javabank5/api/customer";
  }



  async function addUser() {
    const api = "http://localhost:8080/javabank5/api/customer/add";
    
    const response = await fetch(api, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        firstName: add.firstName.value,
        lastName: add.lastName.value,
        email: add.email.value,
        phone: add.phone.value})
    })
  }
    
  document.getElementById("add-button").addEventListener("click", addUser);
