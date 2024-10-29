var compsTbody = document.getElementById("comps-tbody");

function loadComps() {
  var http = new XMLHttpRequest();
  http.onload = function () {
    var response = this.responseText;
    var comps = JSON.parse(response);
    var compText = "";
    for (var i = 0; i < comps.length; i++) {
      var s = comps[i];
      compText += "<tr>";
      compText += "<td>" + s.id + "</td>";
      compText += "<td>" + s.name + "</td>";
      compText +=
        "<td><img src='" + s.image + "' alt='Computer Image' width='50'/></td>";
      compText += "<td>" + s.price + "</td>";
      compText +=
        "<td><button class='btn btn-danger' onclick='deleteComp(" +
        s.id +
        ")'>Delete</button> " +
        "<button class='btn btn-warning' onclick='editComp(" +
        s.id +
        ")'>Update</button></td>";
      compText += "</tr>";
    }
    compsTbody.innerHTML = compText;
  };
  http.open("GET", "http://localhost:8081/api/computer/my-computers", true);
  http.send();
}
function deleteComp(id) {
  var deleteHttp = new XMLHttpRequest();
  deleteHttp.open(
    "DELETE",
    "http://localhost:8081/api/computer/delete/" + id,
    true
  );
  deleteHttp.onload = function () {
    if (this.status === 200) {
      alert("Computer deleted successfully");
      loadComps(); // Перезагрузка данных после успешного удаления
    } else {
      alert("Error deleting computer");
    }
  };
  deleteHttp.send();
}
function resetForm() {
  document.getElementById("category").value = "";
  document.getElementById("ram").value = "";
  document.getElementById("name").value = "";
  document.getElementById("price").value = "";
  document.getElementById("storage").value = "";
  document.getElementById("storageType").value = "";
  document.getElementById("operatingSystem").value = "";
  document.getElementById("graphicsCard").value = "";
  document.getElementById("description").value = "";
  document.getElementById("image").value = "";
}
function newComputer() {
  document.getElementById("new").style.display = "block";
  resetForm(); // Reset form when opening new computer
}

function closeWindow() {
  document.getElementById("new").style.display = "none"; // Hide the form
}

function resetForm() {
  document.getElementById("computerForm").reset();
  document.getElementById("updateId").value = ""; // Clear the hidden field
}

function saveComp(event) {
  event.preventDefault(); // Предотвращаем стандартное поведение формы
  var id = document.getElementById("updateId").value; // Получаем ID из поля ввода
  var category = document.getElementById("category").value;
  var name = document.getElementById("name").value;
  var price = parseFloat(document.getElementById("price").value);
  var ram = parseInt(document.getElementById("ram").value);
  var storage = parseInt(document.getElementById("storage").value);
  var storageType = document.getElementById("storageType").value;
  var operatingSystem = document.getElementById("operatingSystem").value;
  var graphicsCard = document.getElementById("graphicsCard").value;
  var description = document.getElementById("description").value;
  var image = document.getElementById("image").value;

  // Проверяем наличие ID для определения метода запроса
  var method = id ? "PUT" : "POST";
  var url = id
    ? "http://localhost:8081/api/computer/update/" + id // URL для обновления
    : "http://localhost:8081/api/computer/add"; // URL для создания

  var xhr = new XMLHttpRequest();
  xhr.open(method, url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onload = function () {
    if (xhr.status === 200) {
      alert("Computer saved successfully");
      closeWindow(); // Закрываем форму после сохранения
      loadComps(); // Перезагружаем список компьютеров
    } else {
      closeWindow();
      loadComps();
    }
  };

  // Данные компьютера для отправки на сервер
  var computerData = {
    category,
    name,
    price,
    ram,
    storage,
    storageType,
    operatingSystem,
    graphicsCard,
    description,
    image,
  };

  // Отправляем данные на сервер
  xhr.send(JSON.stringify(computerData));
}

function editComp(id) {
  document.getElementById("new").style.display = "block"; // Показываем форму
  resetForm(); // Очищаем форму перед заполнением

  // Получаем текущие данные компьютера
  var editHttp = new XMLHttpRequest();
  editHttp.open(
    "GET",
    "http://localhost:8081/api/computer/" + id + "?t=" + new Date().getTime(),
    true
  );

  editHttp.onload = function () {
    if (this.status === 200) {
      var computer = JSON.parse(this.responseText);
      // Заполняем форму текущими данными
      document.getElementById("updateId").value = computer.id; // Устанавливаем ID для обновления
      document.getElementById("category").value = computer.category;
      document.getElementById("name").value = computer.name;
      document.getElementById("price").value = computer.price;
      document.getElementById("ram").value = computer.ram;
      document.getElementById("storage").value = computer.storage;
      document.getElementById("storageType").value = computer.storageType;
      document.getElementById("operatingSystem").value =
        computer.operatingSystem;
      document.getElementById("graphicsCard").value = computer.graphicsCard;
      document.getElementById("description").value = computer.description;
      document.getElementById("image").value = computer.image;
    } else {
      alert("Error fetching computer details");
    }
  };
  editHttp.send(); // Отправляем запрос на сервер
}

setTimeout(loadComps, 200); // Load computers on page load
