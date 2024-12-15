let responseData;

function getUserTask() {
    // Подготовка начальных данных для запроса /play
    const requestData = {
        encodedLevel: "", // Пустое значение, будет обработано сервером
        words: "",
        wordsUser: ""
    };

    fetch('http://localhost:8080/api/v1/play', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            responseData = data.DataDto; // Получаем объект DataDto из ответа
            let countdown = 5;
            const responseContainer = document.getElementById('responseContainer');

            const updateCountdown = () => {
                if (countdown > 0) {
                    responseContainer.innerText = `${responseData.words} (${countdown})`;
                    countdown--;
                    setTimeout(updateCountdown, 1000); // Повторить через 1 секунду
                } else {
                    // По окончании таймера показываем следующее сообщение
                    document.getElementById('requestGetTask').innerText = 'Попробовать заново';
                    document.getElementById('requestVerifyInput').style.visibility = 'visible';
                    document.getElementById('inputContainer').style.visibility = 'visible';
                    document.getElementById('userInput').style.visibility = 'visible';
                    responseContainer.innerText = 'Теперь введите слово в поле для ввода ниже';
                }
            };
            updateCountdown();

        })
        .catch(error => {
            document.getElementById('responseContainer').innerText = 'Ошибка получения информации из базы данных . . .';
            console.error('Error fetching data:', error);
        });
}

function sendUserInput() {
    const userInput = document.getElementById('userInput').value; // Получаем ввод пользователя

    // Подготовка данных для запроса /play с проверкой
    const requestData = {
        encodedLevel: responseData.encodedLevel, // Пустое значение
        words: responseData.words,
        wordsUser: userInput // Ввод пользователя для проверки
    };

    fetch('http://localhost:8080/api/v1/play', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestData) // Отправляем ввод как JSON
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            // Отображение результата проверки
            const responseContainer = document.getElementById('responseContainer');
            if (data.DataDto.encodedLevel === responseData.encodedLevel) {
                responseContainer.innerText = 'Неверно! Попробуйте еще раз.';
            } else {
                responseContainer.innerText = 'Верно! Вы правильно запомнили текст.';
            }
        })
        .catch(error => {
            document.getElementById('responseContainer').innerText = 'Ошибка проверки данных. Попробуйте снова.';
            console.error('Error verifying data:', error);
        });
}
