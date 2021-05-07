

window.onload = function() {
	getSessUser();
	getPending();
	getApproved();
	getDenied();
}

function getSessUser() {

	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
	if(xhttp.readyState == 4 && xhttp.status == 200){
		let user = JSON.parse(xhttp.responseText);
		//console.log(user);
		document.getElementById("welcomeHeader").innerText = `Welcome ${user.firstName}`;
		}
	}
	
	xhttp.open("GET", "http://localhost:8080/ERS/getsessionuser.json");
	
	xhttp.send();
}

function getPending() {

	let xhttp = new XMLHttpRequest();
	
	xhttp.open("GET", "http://localhost:8080/ERS/getpendingbyemp.json");
	xhttp.onreadystatechange = function() {
	if(xhttp.readyState == 4 && xhttp.status == 200){
		let pendingList = JSON.parse(xhttp.responseText);
		//console.log(pendingList);
		displayExistingRecords(pendingList);
		}
	}
	
	
	xhttp.send();
}

function getApproved() {

	let xhttp = new XMLHttpRequest();
	
	xhttp.open("GET", "http://localhost:8080/ERS/getapprovedbyemp.json");
	xhttp.onreadystatechange = function() {
	if(xhttp.readyState == 4 && xhttp.status == 200){
		let approvedList = JSON.parse(xhttp.responseText);
		//console.log(approvedList);
		displayExistingRecords2(approvedList);
		}
	}
	
	
	xhttp.send();
}


function getDenied() {

	let xhttp = new XMLHttpRequest();
	
	xhttp.open("GET", "http://localhost:8080/ERS/getdeniedbyemp.json");
	xhttp.onreadystatechange = function() {
	if(xhttp.readyState == 4 && xhttp.status == 200){
		let deniedList = JSON.parse(xhttp.responseText);
		//console.log(deniedList);
		displayExistingRecords3(deniedList);
		}
	}
	
	
	xhttp.send();
}

var selectedRow = null

function onFormSubmit() {
    if (validate()) {
        var formData = readFormData();
        if (selectedRow == null)
            insertNewRecord(formData);
        else
            updateRecord(formData);
        resetForm();
    }
}

function readFormData() {
    var formData = {};
    formData["employeeId"] = document.getElementById("employeeId").value;
    formData["amount"] = document.getElementById("amount").value;
    formData["description"] = document.getElementById("description").value;
    formData["type"] = document.getElementById("type").value;
    return formData;
}

function insertNewRecord(data) {
    var table = document.getElementById("reimbursementList").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    cell1 = newRow.insertCell(0);
    cell1.innerHTML = data.employeeId;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = data.amount;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = data.description;
    cell4 = newRow.insertCell(3);
    cell4.innerHTML = data.type;
    cell4 = newRow.insertCell(4);
    cell4.innerHTML = `<a onClick="onEdit(this)">Process</a>
                       <a onClick="onDelete(this)">Delete</a>`;
}


function displayExistingRecords(data){

 var table = document.getElementById("reimbursementList").getElementsByTagName('tbody')[0];

for(i=0; i < data.length; i++){
 	var newRow = table.insertRow(table.length);
	cell1 = newRow.insertCell(0);
    cell1.innerHTML = data[i].reimbId;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = data[i].amount;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = data[i].description;
    cell4 = newRow.insertCell(3);
    var typeName;
	var type= data[i].reimbTypeId;
	if (data[i].reimbTypeId == 1) {
	typeName = 'Parking'}
	else if (data[i].reimbTypeId == 2) {
	typeName = 'Flight'
	}
	else if (data[i].reimbTypeId == 3) {
	typeName = 'Materials'
	}
	else if (data[i].reimbTypeId == 4) {
	typeName = 'Certs'
	}
	else {
	typeName = 'Misc';
	}
	
    cell4.innerHTML = type + " -  " + typeName;

	}

}

function displayExistingRecords2(data){

 var table2 = document.getElementById("approvedList").getElementsByTagName('tbody')[0];

for(i=0; i < data.length; i++){
 	var newRow = table2.insertRow(table2.length);
	cell1 = newRow.insertCell(0);
    cell1.innerHTML = data[i].reimbId;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = data[i].amount;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = data[i].description;
    cell4 = newRow.insertCell(3);
    var typeName;
	var type= data[i].reimbTypeId;
	if (data[i].reimbTypeId == 1) {
	typeName = 'Parking'}
	else if (data[i].reimbTypeId == 2) {
	typeName = 'Flight'
	}
	else if (data[i].reimbTypeId == 3) {
	typeName = 'Materials'
	}
	else if (data[i].reimbTypeId == 4) {
	typeName = 'Certs'
	}
	else {
	typeName = 'Misc';
	}
	
    cell4.innerHTML = type + " -  " + typeName;

	}

}



function displayExistingRecords3(data){

 var table3 = document.getElementById("deniedList").getElementsByTagName('tbody')[0];

for(i=0; i < data.length; i++){
 	var newRow = table3.insertRow(table3.length);
	cell1 = newRow.insertCell(0);
    cell1.innerHTML = data[i].reimbId;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = data[i].amount;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = data[i].description;
    cell4 = newRow.insertCell(3);
    var typeName;
	var type= data[i].reimbTypeId;
	if (data[i].reimbTypeId == 1) {
	typeName = 'Parking'}
	else if (data[i].reimbTypeId == 2) {
	typeName = 'Flight'
	}
	else if (data[i].reimbTypeId == 3) {
	typeName = 'Materials'
	}
	else if (data[i].reimbTypeId == 4) {
	typeName = 'Certs'
	}
	else {
	typeName = 'Misc';
	}
	
    cell4.innerHTML = type + " -  " + typeName;

	}

}



function resetForm() {
    document.getElementById("employeeId").value = "";
    document.getElementById("amount").value = "";
    document.getElementById("description").value = "";
    document.getElementById("type").value = "";
    selectedRow = null;
}

function onEdit(td) {
    selectedRow = td.parentElement.parentElement;
    document.getElementById("employeeId").value = selectedRow.cells[0].innerHTML;
    document.getElementById("amount").value = selectedRow.cells[1].innerHTML;
    document.getElementById("description").value = selectedRow.cells[2].innerHTML;
    document.getElementById("type").value = selectedRow.cells[3].innerHTML;
}
function updateRecord(formData) {
    selectedRow.cells[0].innerHTML = formData.employeeId;
    selectedRow.cells[1].innerHTML = formData.amount;
    selectedRow.cells[2].innerHTML = formData.description;
    selectedRow.cells[3].innerHTML = formData.type;
}

function onDelete(td) {
    if (confirm('Are you sure to delete this record ?')) {
        row = td.parentElement.parentElement;
        document.getElementById("reimbursementList").deleteRow(row.rowIndex);
        resetForm();
    }
}
function validate() {
    isValid = true;
    if (document.getElementById("employeeId").value == "") {
        isValid = false;
        document.getElementById("fullNameValidationError").classList.remove("hide");
    } else {
        isValid = true;
        if (!document.getElementById("fullNameValidationError").classList.contains("hide"))
            document.getElementById("fullNameValidationError").classList.add("hide");
    }
    return isValid;
}