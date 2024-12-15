function getUserTask(){
    fetch('http://localhost:8080/api/v1/sendUserTask')
        .then(response => {
            if (!response.ok) {throw new Error(`HTTP error! status: ${response.status}`);}
            return response.json();
        })
        .then(data => {
            let countdown = 5;
            const responseContainer = document.getElementById('responseContainer');

            const updateCountdown = () => {
                if (countdown > 0) {
                    responseContainer.innerText = `${data.message} (${countdown})`;
                    countdown--;
                    setTimeout(updateCountdown, 1000); // Call the function again after 1 second
                }
                else {
                    // Once the countdown is complete, show the next message
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
        });

}

function sendUserInput() {
    const userInput = document.getElementById('userInput').value; // Get user input

    fetch('http://localhost:8080/api/v1/verifyUserData', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ input: userInput }) // Send input as JSON
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            // Display verification result
            const responseContainer = document.getElementById('responseContainer');
            if (data.correct) {
                responseContainer.innerText = 'Верно! Вы правильно запомнили текст.';
            } else {
                responseContainer.innerText = 'Неверно! Попробуйте еще раз.';
            }
        })
        .catch(error => {
            document.getElementById('responseContainer').innerText = 'Ошибка проверки данных. Попробуйте снова.';
            console.error('Error verifying data:', error);
        });
}
