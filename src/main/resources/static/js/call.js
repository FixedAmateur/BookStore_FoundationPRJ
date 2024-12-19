fetch("https://book-application-xpcr.onrender.com/books")
.then(function(response){
    return response.json()
}).then(function(books){
    let htmls = books.map(function(book){
        return `<div class="col-lg-3 col-md-6 mb-4">
        <div class="card">
            <div class="bg-image hover-zoom ripple ripple-surface ripple-surface-light" data-mdb-ripple-color="light">
                <img src="${book.image}" class="w-100" style="width:296px; height:445px">
                <a href="https://book-application-xpcr.onrender.com/book/${book.id}">
                    <div class="mask">
                        <div class="d-flex justify-content-start align-items-end h-100">
                            <h5><span class="badge bg-dark ms-2">NEW</span></h5>
                        </div>
                    </div>
                    <div class="hover-overlay">
                        <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                    </div>
                </a>
            </div>
            <div class="card-body">
                <a href="" class="text-reset">
                    <h5 class="card-title mb-2" style="white-space: nowrap; overflow:hidden; text-overflow: ellipsis">${book.title}</h5>
                </a>
                <a href="" class="text-reset ">
                    <p>Softcover</p>
                </a>
                <h6 class="mb-3 price">${book.price}₫</h6>
            </div>
        </div>
    </div>`
    })
    document.getElementById("books").innerHTML = htmls.join('')
})
