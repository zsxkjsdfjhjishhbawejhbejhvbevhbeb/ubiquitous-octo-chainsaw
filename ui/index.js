'use strict';
const req = new XMLHttpRequest();

req.onload = (res) => {
    if(res.target.responseURL == "http://localhost:8080/getAll"){
        create_table(JSON.parse(res.target.response))
    } else if (res.target.responseURL == "http://localhost:8080/create"){
        get_all()
    } else if (res.target.responseURL == "http://localhost:8080/update"){
        get_all()
    }
}

function create(){
    req.open("POST", "http://localhost:8080/create");
    req.setRequestHeader("Content-Type", "application/json");

    let title = document.getElementById("title").value
    let author = document.getElementById("author").value
    let description = document.getElementById("description").value
    let status = document.getElementById("status").value
    let urgency = document.getElementById("urgency").value
    console.log(JSON.stringify({
                        "title": title,
                        "author": author,
                        "description": description,
                        "status": status,
                        "urgency": urgency
                    }))
    req.send(JSON.stringify({
        "title": title,
        "author": author,
        "description": description,
        "status": status,
        "urgency": urgency
    }));
}

function get_all(){
    req.open("GET", "http://localhost:8080/getAll");
    req.send();
}

function update_status(e){
    let id = e.parentElement.parentElement.children[0].innerHTML
    let status = e.value

    req.open("POST", "http://localhost:8080/update");
    req.setRequestHeader("Content-Type", "application/json");
    req.send(JSON.stringify({
        "id": id,
        "status": status
    }));
}

function update_solution(e){
    let id = e.parentElement.parentElement.children[0].innerHTML
    let solution = e.value

    req.open("POST", "http://localhost:8080/update");
    req.setRequestHeader("Content-Type", "application/json");
    req.send(JSON.stringify({
        "id": id,
        "status": "done",
        "solution": solution
    }));
}

function create_table(data){
    let order = document.querySelectorAll('input[name="order"]');
    let keys = document.querySelectorAll('input[name="key"]');

    for (let checkbox of keys){
        if(checkbox.checked){
            var key = checkbox.value
            break
        }
    }

    data = data.sort((a, b) => (eval("(a."+ key +(order[0].checked ? " >" : " <") +" b."+key+") ? 1 : -1")))
    let table = document.getElementById("table").getElementsByTagName('tbody')[0];
    table.innerHTML = ""

    for ( let row of data ){
        let table_row = table.insertRow()

        let id = table_row.insertCell()
        let title = table_row.insertCell()
        let author = table_row.insertCell()
        let description = table_row.insertCell()
        let time_created = table_row.insertCell()
        let status = table_row.insertCell()
        let urgency = table_row.insertCell()
        let solution = table_row.insertCell()

        id.innerHTML = row.id
        title.innerHTML = row.title
        author.innerHTML = row.author
        description.innerHTML = row.description
        time_created.innerHTML = row.timeCreated
        status.innerHTML = '<input type="text" value="'+row.status+'" onChange="update_status(this)">'
        urgency.innerHTML = row.urgency
        solution.innerHTML = '<input type="text" value="'+ (row.solution ? row.solution : '')  +'" onChange="update_solution(this)" '+(row.status == "done" ? '' : 'disabled') +'>'
    }

    let new_row = table.insertRow(table.rows.length)
    let id = new_row.insertCell()
    let title = new_row.insertCell()
    let author = new_row.insertCell()
    let description = new_row.insertCell()
    let time_created = new_row.insertCell()
    let status = new_row.insertCell()
    let urgency = new_row.insertCell()
    let solution = new_row.insertCell()

    id.innerHTML = '<input type="text" value="'+table.rows.length+'"disabled>'
    title.innerHTML = '<input type="text" id="title">'
    author.innerHTML = '<input type="text" id="author">'
    description.innerHTML = '<input type="text" id="description">'
    time_created.innerHTML = '<input type="text" disabled>'
    status.innerHTML = '<input type="text" id="status">'
    urgency.innerHTML = '<input type="text" id="urgency">'
    solution.innerHTML = '<input type="text" disabled>'
}

document.addEventListener("DOMContentLoaded", function(event) {
  get_all()
});