const API = "http://localhost:8081/api/blogs";
const blogsDiv = document.getElementById("blogs");

// CREATE
document.getElementById("createForm").addEventListener("submit", e => {
    e.preventDefault();

    const fd = new FormData();
    fd.append("title", title.value);
    fd.append("content", content.value);
    if (image.files[0]) fd.append("image", image.files[0]);

    fetch(API + "/create", { method: "POST", body: fd })
        .then(() => {
            e.target.reset();
            loadBlogs();
        });
});

// READ
function loadBlogs() {
    fetch(API)
        .then(res => res.json())
        .then(data => {
            blogsDiv.innerHTML = "";
            data.forEach(b => {
                blogsDiv.innerHTML += `
                <div class="blog">
                    <h3>${b.title}</h3>
                    <p>${b.content}</p>
                    ${b.imageName ? `<img src="/uploads/${b.imageName}">` : ""}
                    <br>
                    <button onclick="editBlog(${b.id}, '${b.title}', '${b.content}')">Edit</button>
                    <button class="delete" onclick="deleteBlog(${b.id})">Delete</button>
                </div>`;
            });
        });
}

// EDIT
function editBlog(id, title, content) {
    editCard.classList.remove("hidden");
    editId.value = id;
    editTitle.value = title;
    editContent.value = content;
    window.scrollTo(0, 0);
}

// UPDATE
document.getElementById("editForm").addEventListener("submit", e => {
    e.preventDefault();

    const fd = new FormData();
    fd.append("title", editTitle.value);
    fd.append("content", editContent.value);
    if (editImage.files[0]) fd.append("image", editImage.files[0]);

    fetch(API + "/" + editId.value, { method: "PUT", body: fd })
        .then(() => {
            editCard.classList.add("hidden");
            loadBlogs();
        });
});

// DELETE
function deleteBlog(id) {
    if (confirm("Delete this blog?")) {
        fetch(API + "/" + id, { method: "DELETE" })
            .then(loadBlogs);
    }
}

loadBlogs();
