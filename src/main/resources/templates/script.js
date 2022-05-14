const submitBtn = document.querySelector('.submitBtn');
const form = document.querySelector('form');

const API_URL = 'http://localhost:8080/api/user/create';

const sendData = async (userObject) => {
	const response = await fetch(API_URL, {
		method: 'POST',
		headers: {
			Accept: 'application/json',
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(userObject),
	});

	const responseData = await response.json();

	console.log(responseData);
};

form.addEventListener('submit', (e) => {
	e.preventDefault();
	const userObject = {
		name: document.querySelector('.name').value,
		email: document.querySelector('.email').value,
	};

	sendData(userObject);
});
