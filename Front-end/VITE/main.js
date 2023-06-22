fetch('http://localhost:8080/api/content/')
.then (respoonse=>respoonse.json())
.then(data=>console.log(data));