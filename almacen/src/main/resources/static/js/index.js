window.addEventListener('DOMContentLoaded', async function() {
	const respuesta = await fetch('http://localhost:8081/productos');
	const productos = await respuesta.json();
	
	console.log(productos);
});