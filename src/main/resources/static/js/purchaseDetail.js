var purch_id = document.querySelectorAll('[id=p_id]');
for (var i = 0; i< purch_id.length; i++){
    fetch(purch_id[i].value)
    .then(function(response){
    return response.json()
    }).then(function(purchase){
        document.getElementById(`${purchase.p_id}count`).innerHTML = `Item count: ${purchase.count}`
        document.getElementById(`${purchase.p_id}total`).innerHTML = `Total (excl. delivery): ${purchase.total}₫`
    })
}