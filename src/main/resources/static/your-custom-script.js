$(document).ready(function () {
    // Обработка отправки формы
    $("#cargoForm").submit(function (event) {
        event.preventDefault();

        // Получение данных из формы
        var formData = {
            cargoName: $("#cargoName").val(),
            cargoDescription: $("#cargoDescription").val(),
            deliveryDate: $("#deliveryDate").val()
        };

        // Отправка AJAX-запроса на сервер
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/cargo/add", // Замените на ваш URL-маппинг в Spring контроллере
            data: JSON.stringify(formData),
            success: function (data) {
                // Обновление таблицы после успешного добавления груза
                updateCargoTable();
            },
            error: function (error) {
                console.log("Error:", error);
            }
        });
    });

    // Функция для обновления таблицы с грузами
    function updateCargoTable() {
        // Очистка текущих данных в таблице
        $("#cargoTableBody").empty();

        // Здесь вы можете отправить запрос на сервер для получения списка грузов и заполнить таблицу
        // Пример: $.get("/api/cargo/all", function(data) {...});

        // Временные данные для примера
        var dummyData = [
            { id: 1, cargoName: "Груз 1", cargoDescription: "Описание груза 1", deliveryDate: "2023-11-23" },
            { id: 2, cargoName: "Груз 2", cargoDescription: "Описание груза 2", deliveryDate: "2023-11-24" }
        ];

        // Заполнение таблицы данными
        $.each(dummyData, function (index, cargo) {
            $("#cargoTableBody").append(
                "<tr><td>" + cargo.id + "</td><td>" + cargo.cargoName + "</td><td>" +
                cargo.cargoDescription + "</td><td>" + cargo.deliveryDate + "</td><td>" +
                "<button class='btn btn-danger btn-sm' onclick='deleteCargo(" + cargo.id + ")'>Удалить</button>" +
                "</td></tr>"
            );
        });
    }

    // Вызов функции для первоначального заполнения таблицы
    updateCargoTable();
});

// Функция для удаления груза
function deleteCargo(cargoId) {
    // Отправка AJAX-запроса на сервер для удаления груза
    $.ajax({
        type: "DELETE",
        url: "/api/cargo/delete/" + cargoId, // Замените на ваш URL-маппинг в Spring контроллере
        success: function (data) {
            // Обновление таблицы после успешного удаления груза
            updateCargoTable();
        },
        error: function (error) {
            console.log("Error:", error);
        }
    });
}