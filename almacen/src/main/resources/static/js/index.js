const URL = 'http://app:8081/productos/';
const URLcategorias = 'http://app:8081/categorias/'

let form;
let listado;

let id, nombre, precio, stock, fechaCaducidad, categoria;

window.addEventListener('DOMContentLoaded', async function() {
	form = document.querySelector('form');
	listado = document.querySelector('table');

	id = form.querySelector('#id');
	nombre = form.querySelector('#nombre');
	precio = form.querySelector('#precio');
	stock = form.querySelector('#stock');
	fechaCaducidad = form.querySelector('#fechaCaducidad');
	categoria = form.querySelector('#categoria');

	form.addEventListener('submit', aceptar);

	cargarCategorias();

	mostrarListado();
});

async function borrar(id) {
	const respuesta = await fetch(URL + id, {
		method: 'DELETE'
	});

	console.log(respuesta);

	refrescarTabla();
}

async function refrescarTabla() {
	const respuesta = await fetch(URL);
	const productos = await respuesta.json();

	const tbody = document.querySelector('tbody');

	tbody.innerHTML = '';

	productos.forEach(p => {
		const tr = document.createElement('tr');

		tr.innerHTML = `
			<th>${p.id}</th>
			<td>${p.nombre}</td>
			<td>${p.precio}</td>
			<td>${p.stock}</td>
			<td>${p.fechaCaducidad}</td>
			<td>${p.categoria.nombre}</td>
			<td>
				<a href="javascript:mostrarFormulario(${p.id})">Editar</a>
				<a href="javascript:borrar(${p.id})">Borrar</a>
			</td>
		`;

		tbody.appendChild(tr);
	});

	console.log(productos);
}

async function mostrarFormulario(idSeleccionado) {
	form.style.display = 'block';
	listado.style.display = 'none';

	if (idSeleccionado) {
		const respuesta = await fetch(URL + idSeleccionado);
		const producto = await respuesta.json();

		id.value = idSeleccionado;
		nombre.value = producto.nombre;
		precio.value = producto.precio;
		stock.value = producto.stock;
		fechaCaducidad.value = producto.fechaCaducidad;
		categoria.value = producto.categoria.id;
	}
}

function mostrarListado() {
	form.style.display = 'none';
	listado.style.display = 'table';

	id.value = '';
	nombre.value = '';
	precio.value = '';
	stock.value = '';
	fechaCaducidad.value = '';
	categoria.value = '';
	
	refrescarTabla();
}

async function aceptar(e) {
	e.preventDefault();

	const producto = { id: id.value, nombre: nombre.value, precio: precio.value, stock: stock.value, fechaCaducidad: fechaCaducidad.value, categoria: { id: categoria.value } };

	console.log(producto);

	let metodo;
	let url = URL;

	if (producto.id) {
		metodo = 'PUT';
		url += producto.id;
	} else {
		metodo = 'POST';
	}

	const respuesta = await fetch(url, {
		method: metodo,
		body: JSON.stringify(producto),
		headers: {
			'Content-Type': 'application/json'
		}
	});

	console.log(respuesta);

	mostrarListado();
}

async function cargarCategorias() {
	const respuesta = await fetch(URLcategorias);
	const categorias = await respuesta.json();

	const select = document.querySelector('#categoria');

	categorias.forEach(c => {
		const option = document.createElement('option');
		option.value = c.id;
		option.innerText = c.nombre;

		select.appendChild(option);
	});
}