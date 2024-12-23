let isNotFirstDeploy = false;
let responseData = {
    encodedLevel: "",
    words: "",
    wordsUser: "",
    total: 5,
    text: "1й этап: На данном этапе вам надо ввести его \"перевернутым\"  (столб - блотс)\n" +
        "2й этап: На данном этапе вам надо ввести слова в любом порядке\n" +
        "3й этап: На данном этапе вам надо ввести слова в правильном порядке\n" +
        "4й этап: На данном этапе вам надо ввести слова перевернутыми в любом порядке\n" +
        "5й этап: На данном этапе вам надо ввести слова в перевернутыми в правильном порядке",
    isCorrect: ""
};
let lastFetchedData = null; // Глобальная переменная для хранения последних данных

const userInput = document.getElementById('userInput');
const requestGetTask = document.getElementById('requestGetTask');
const arrow = document.querySelector('.arrow');
const taskTextBox = document.getElementById('taskTextBox');
const showLevelConditionButton = document.getElementById('showLevelCondition');


async function fetchData() {
    try {
        const response = await fetch('https://memorytrainng-production.up.railway.app/api/v1/play', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(responseData),
        });
        return await response.json();
    } catch (error) {
        document.getElementById('taskTextBox').innerText = 'Ошибка получения информации из базы данных . . .';
        console.error('Error fetching data:', error);
    }
}

function showPopup(text) {
    const popup = document.getElementById('popup');
    const popupText = document.getElementById('popupText');
    popupText.innerText = text;
    popup.style.display = 'block'; // Показываем всплывающее окно
}

function closePopup() {
    const popup = document.getElementById('popup');
    popup.style.display = 'none'; // Скрываем всплывающее окно
}

// Модифицируем функцию showLevelCondition
function showLevelCondition() {
    if (lastFetchedData && lastFetchedData.DataDto.text) {
        showPopup(lastFetchedData.DataDto.text);
    } else {
        showPopup("1й этап: На данном этапе вам надо ввести его \"перевернутым\"  (столб - блотс)\n" +
        "2й этап: На данном этапе вам надо ввести слова в любом порядке\n" +
        "3й этап: На данном этапе вам надо ввести слова в правильном порядке\n" +
        "4й этап: На данном этапе вам надо ввести слова перевернутыми в любом порядке\n" +
        "5й этап: На данном этапе вам надо ввести слова в перевернутыми в правильном порядке");
    }
}

document.addEventListener('DOMContentLoaded', () => {
    userInput.focus(); // Устанавливаем курсор в поле ввода при загрузке страницы
});

async function getUserTask() {
    userInput.style.visibility = 'hidden';
    requestGetTask.style.visibility = 'hidden';
    arrow.style.visibility = 'hidden'; // Скрыть стрелку
    responseData.wordsUser = userInput.value;
    userInput.value = "";

    const data = await fetchData();
    lastFetchedData = data; // Сохраняем данные
    console.log(data);

    updateCountdown(data, data.DataDto.total + 2, data.DataDto.total);
    userInput.focus(); // Устанавливаем фокус обратно
}

const updateCountdown = (data, countdown, total) => {
    console.log(countdown);
    if (countdown > total) {
        console.log(data);
        if (data.DataDto.encodedLevel === responseData.encodedLevel) {
            taskTextBox.innerText = data.DataDto.isCorrect;
        } else {
            if (isNotFirstDeploy) {
                document.getElementById('requestGetTask').innerText = 'Следующее слово';
                taskTextBox.innerText = data.DataDto.isCorrect;
            }
        }
        setTimeout(() => updateCountdown(data, countdown - 1, total), 1000);
    } else {
        if (countdown > 0) {
            taskTextBox.innerText = `${data.DataDto.words} (${countdown})`;
            setTimeout(() => updateCountdown(data, countdown - 1, total), 1000);
        } else {
            requestGetTask.style.visibility = 'visible';
            userInput.style.visibility = 'visible';
            requestGetTask.innerText = 'Проверить';
            taskTextBox.innerText = 'Теперь введите слово в поле для ввода ниже';
            responseData = data.DataDto;
            isNotFirstDeploy = true;

            // Делаем кнопку условий уровня видимой
            showLevelConditionButton.style.visibility = 'visible';
            userInput.focus(); // Устанавливаем фокус обратно
        }
    }
};


