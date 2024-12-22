let isNotFirstDeploy = false;
let responseData = {
    encodedLevel: "",
    words: "",
    wordsUser: "",
    total: 5
};
const userInput = document.getElementById('userInput');
const requestGetTask = document.getElementById('requestGetTask');
const arrow = document.querySelector('.arrow');
const taskTextBox = document.getElementById('taskTextBox');

async function getUserTask() {
    userInput.style.visibility = 'hidden';
    requestGetTask.style.visibility = 'hidden';
    arrow.style.visibility = 'hidden'; // Скрыть стрелку
    responseData.wordsUser = userInput.value;
    userInput.value = "";
    const data = await fetchData();
    console.log(data);

    updateCountdown(data, data.DataDto.total + 2, data.DataDto.total);
}

async function fetchData() {
    try {
        const response = await fetch('https://memorytrainng-production.up.railway.app/api/v1/play', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(responseData)
        })
        return await response.json()
    } catch (Error){
        document.getElementById('taskTextBox').innerText = 'Ошибка получения информации из базы данных . . .';
        console.error('Error fetching data:', Error);
    }
}

const updateCountdown = (data, countdown, total) => {
    console.log(countdown);
    if (countdown > total) {
        console.log(data);
        if (data.DataDto.encodedLevel === responseData.encodedLevel) {
            taskTextBox.innerText = 'Неверно! Попробуйте еще раз.';
        } else {
            if (isNotFirstDeploy) {
                document.getElementById('requestGetTask').innerText = 'Следующее слово';
                taskTextBox.innerText = 'Верно! Вы правильно запомнили текст.';
            }
        }
        setTimeout(() => updateCountdown(data, countdown-1, total), 1000);
    } else {
        if (countdown > 0) {
            taskTextBox.innerText = `${data.DataDto.words} (${countdown})`;
            setTimeout(() => updateCountdown(data, countdown-1, total), 1000);
        } else {
            requestGetTask.style.visibility = 'visible';
            userInput.style.visibility = 'visible';
            requestGetTask.innerText = 'Проверить';
            taskTextBox.innerText = 'Теперь введите слово в поле для ввода ниже';
            responseData = data.DataDto;
            isNotFirstDeploy = true;
        }
    }
};