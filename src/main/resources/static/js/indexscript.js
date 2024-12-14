function onButtonClick(){
    fetch('http://localhost:8080/api/v1/test_method')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // Assuming server responds with JSON data
        })
        .then(data => {
            // Update the web page with the data
            document.getElementById('responseContainer').innerText = JSON.stringify(data, null, 2);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
            document.getElementById('responseContainer').innerText = 'Error fetching data!';
        });
}