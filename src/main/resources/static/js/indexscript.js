let isNotFirstDeploy = false;
let countdown;
let responseData= {
    encodedLevel: "",
    words: "",
    wordsUser: ""
};


function getUserTask() {
    document.getElementById('userInput').style.visibility = 'hidden';
    document.getElementById('requestGetTask').style.visibility = 'hidden';
    responseData.wordsUser = document.getElementById('userInput').value;
    document.getElementById('userInput').value = "";
    fetch('http://localhost:8080/api/v1/play', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(responseData)
    })
        .then(response => {
            if (!response.ok) {throw new Error(`HTTP error! status: ${response.status}`);   }
            return response.json();
        })
        .then(data => {
            const taskTextBox = document.getElementById('taskTextBox');
            countdown = 7;

            const updateCountdown = () => {
                if (countdown > 5) {
                    if (data.DataDto.encodedLevel === responseData.encodedLevel) {
                        taskTextBox.innerText = 'Неверно! Попробуйте еще раз.';
                    }
                    else {
                        if (isNotFirstDeploy) {
                            document.getElementById('requestGetTask').innerText = 'Следующее слово';
                            taskTextBox.innerText = 'Верно! Вы правильно запомнили текст.';
                        }
                    }
                    countdown--;
                    setTimeout(updateCountdown, 1000);
                }
                else {
                    if (countdown > 0) {
                        taskTextBox.innerText = `${data.DataDto.words} (${countdown})`;
                        countdown--;
                        setTimeout(updateCountdown, 1000);
                    }
                    else {
                        document.getElementById('requestGetTask').style.visibility = 'visible';
                        document.getElementById('userInput').style.visibility = 'visible';
                        document.getElementById('requestGetTask').innerText = 'Проверить';
                        taskTextBox.innerText = 'Теперь введите слово в поле для ввода ниже';
                        responseData = data.DataDto;
                        isNotFirstDeploy = true;
                    }
                }
            };
            updateCountdown();
        })
        .catch(error => {
            document.getElementById('taskTextBox').innerText = 'Ошибка получения информации из базы данных . . .';
            console.error('Error fetching data:', error);
        });
}