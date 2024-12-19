const URL = document.getElementById("variable")
fetch(URL.value)
.then(function(response){
return response.json()
}).then(function(pds){
document.getElementById("display").innerHTML = `${pds.length}`
})