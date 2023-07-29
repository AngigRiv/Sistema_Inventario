const ventaUrl = "/api/ventas";
const productoUrl = "/api/productos";
var productoMetodo = "";

function saveVenta() {
	
	if ($("#total").val() == 0) {
    Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Seleccione al menos un producto.',
        showConfirmButton: false,
        timer: 3000
    });
    return;
}

// Verificar si no se ha seleccionado ningún cliente

    // Obtener los datos necesarios para crear la venta
let detalles = [];

    // Recorrer la tabla de detalle de venta para obtener los detalles de los productos
    $("#tablaDetalleVenta").DataTable().rows().every(function () {
        let productoId = this.data()[0];
        let cantidad = parseInt(this.node().querySelector(".cantidad").value);
        let subtotal = parseFloat(this.node().querySelector(".subtotal").textContent);
        detalles.push({
            producto_id: { id: productoId },
            cantidad: cantidad,
            total: subtotal
        });
      
    });
  

    // Crear el objeto venta para enviar en la petición AJAX
    let venta = {
        fecha: new Date(),
        estado: "Vigente", // Puedes asignar la fecha actual aquí o recuperarla desde otro lugar
        total: parseFloat($("#total").val()),
       
    };
	 
    // Realizar la petición AJAX para crear la venta
    $.ajax({
        type: "POST",
        url: ventaUrl,
        data: JSON.stringify(venta),
        dataType: "text",
        contentType: "application/json",
        cache: false,
        success: function (data) {
		
				 let ventaData = JSON.parse(data);
				 console.log(ventaData.id)
            Swal.fire({
                icon: 'success',
                title: 'Venta registrada correctamente',
                showConfirmButton: false,
                timer: 1500
            });
            let detalles = [];
            $("#tablaDetalleVenta").DataTable().rows().every(function () {
        let productoId = this.data()[0];
        let cantidad = parseInt(this.node().querySelector(".cantidad").value);
        let subtotal = parseFloat(this.node().querySelector(".subtotal").textContent);
        detalles.push({
            producto_id: { id: productoId },
            cantidad: cantidad,
            total: subtotal,
            venta: { id: ventaData.id }
        });
      
    });
saveDetalles(detalles);
            // Limpiar los campos y la tabla de detalle de venta para una nueva venta
            clearVenta();
            $("#tablaDetalleVenta").DataTable().clear().draw(false);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("Error en la petición AJAX:", textStatus, errorThrown);

            Swal.fire({
                icon: 'error',
                title: 'Error en la petición AJAX',
                text: 'Hubo un problema al registrar la venta. Por favor, inténtalo de nuevo más tarde.',
                showConfirmButton: false,
                timer: 3000
            });
        }
    });
}
function saveDetalles(detalles) {

    // Crear el objeto venta para enviar en la petición AJAX
    // Realizar la petición AJAX para crear la venta
   
    
     detalles.forEach(function (detalle) {
		  console.log(detalle)
    $.ajax({
        type: "POST",
        url: "/api/detalleventas",
        data: JSON.stringify(detalle),
        dataType: "text",
        contentType: "application/json",
        cache: false,
        success: function (data) {
			

        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.error("Error en la petición AJAX:", textStatus, errorThrown);

            Swal.fire({
                icon: 'error',
                title: 'Error en la petición AJAX',
                text: 'Hubo un problema al registrar la venta. Por favor, inténtalo de nuevo más tarde.',
                showConfirmButton: false,
                timer: 3000
            });
        }
    });
    });
}

function getTablaProducto() {
    $.ajax({
        type: "GET",
        url: productoUrl,
        dataType: "text",
        cache: false,
        success: function (data) {
            let t = $("#tablaProductos").DataTable({
				"lengthMenu": [3, 4, 5],
                language: {
                    lengthMenu: "Mostrar _MENU_ registros",
                    zeroRecords: "No se encontraron coincidencias",
                    info: "Mostrando del _START_ al _END_ de _TOTAL_",
                    infoEmpty: "Sin resultados",
                    search: "Buscar: ",
                    paginate: {
                        first: "Primero",
                        last: "Último",
                        next: "Siguiente",
                        previous: "Anterior",
                    },
                },
                columnDefs: [
                    { targets: 0, width: "10%" },
                    { targets: 1, width: "30%" },
                    { targets: 2, width: "15%" },
                    { targets: 3, width: "20%" },
                    { targets: 4, width: "10%" },
                    { targets: 5, orderable: false, width: "15%" }
                ],
            });

            t.clear().draw(false);

            let botonera = '<button type="button" class="btn btn-success btn-sm seleccionar">Agregar <i class="fas fa-cart-plus text-white"></i></button>';

            let js = JSON.parse(data);

            $.each(js, function (index, producto) {
			
                t.row.add([
                    producto.id,
                    producto.nombre,
                    producto.precio.toFixed(2),
                    producto.descripcion,
                    producto.stock,
                          producto.categoria_id.nombre, // Suponiendo que el objeto "marca" en Producto tiene un atributo "nombreMarca".
                    producto.marca_id.nombre, // Suponiendo que el objeto "categoria" en Producto tiene un atributo "nombreCategoria".
                    producto.unidad_id.nombre,
                    botonera
                ]);
            });

            t.draw(false);

        },
    }).fail(function () {
        // Manejar error en caso de que falle la petición AJAX.
    });
}


    
function setFilaProductoToDetalles(id) {
    $.ajax({
        type: "GET",
        url: productoUrl + "/" + id,
        cache: false,
        timeout: 600000,
        success: function (data) {
            // Recuperar los atributos del producto seleccionado
            let producto = {
                id: data.id,
                nombre: data.nombre,
                precio: data.precio.toFixed(2),
                // Otros atributos que necesites agregar a la tabla de detalle de venta
            };

            // Agregar el producto a la tabla de detalle de venta
            agregarProductoADetalleVenta(producto);

            // ... Código existente ...
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error en la petición AJAX',
                text: 'Hubo un problema al obtener los datos del producto. Por favor, inténtalo de nuevo más tarde.',
                showConfirmButton: false,
                timer: 3000
            });
        }
    });
}
let contadorFilas = 0; // Variable para llevar el conteo de filas agregadas

function mostrarMensajeProductoExistente() {
    Swal.fire({
        icon: 'info',
        title: 'Producto Repetido',
        text: 'Este producto ya ha sido agregado a la venta. Puedes actualizar la cantidad en la tabla.',
        confirmButtonColor: '#3085d6',
        confirmButtonText: 'Ok'
    });
}

function agregarProductoADetalleVenta(producto) {
    // Obtener la tabla de detalle de venta
    let tablaDetalleVenta = $("#tablaDetalleVenta").DataTable();

    // Buscar el producto en la tabla por su ID
    let productoExistente = tablaDetalleVenta.rows().data().filter(function (fila) {
        return fila[0] === producto.id;
    });

    if (productoExistente.length > 0) {
        // Si el producto ya existe, mostrar el mensaje y no agregar una nueva fila
        mostrarMensajeProductoExistente();
        return;
    }

    // Generar un ID único para los elementos de esta fila
    contadorFilas++;
    let filaId = 'fila-' + contadorFilas;
	precio =    producto.precio;
    // Agregar una nueva fila con los datos del producto
    tablaDetalleVenta.row.add([
        producto.id,
        producto.nombre,
        precio,
        '<input type="number" min="1" class="form-control cantidad" value="1" id="cantidad-' + filaId + '">',
        '<span class="subtotal" id="subtotal-' + filaId + '">' + precio + '</span>',
        '<button type="button" class="btn btn-danger btn-sm eliminar" data-fila="' + filaId + '">Eliminar <i class="fas fa-trash"></i></button>'
    ]).draw(false);

    // Asignar un evento para actualizar el subtotal cuando se modifique la cantidad
    $("#cantidad-" + filaId).on("input", function () {
        let cantidad = parseInt($(this).val());
        if (isNaN(cantidad) || cantidad < 1) {
            cantidad = 1;
        }
        let precioUnitario = parseFloat(producto.precio);
        let subtotal = cantidad * precioUnitario;
        $("#subtotal-" + filaId).text(subtotal.toFixed(2));
        calcularYMostrarTotal()
    });
    calcularYMostrarTotal();
}

// Evento para eliminar la fila seleccionada
$(document).on('click', '.eliminar', function () {
    let filaId = $(this).data('fila');
    $("#" + filaId).remove();
    
        $(this).closest('tr').remove();

    // Volver a dibujar la tabla para reflejar los cambios
    $("#tablaDetalleVenta").DataTable().draw(false);
    calcularYMostrarTotal();
});
$(document).ready(function () {
    $.fn.dataTable.ext.errMode = 'none';


$("#tablaDetalleVenta").DataTable({
	"lengthMenu": [3, 4, 5],
    language: {
        lengthMenu: "Mostrar _MENU_ registros",
        zeroRecords: "No se encontraron coincidencias",
        info: "Mostrando del _START_ al _END_ de _TOTAL_",
        infoEmpty: "Sin resultados",
        search: "Buscar: ",
        paginate: {
            first: "Primero",
            last: "Último",
            next: "Siguiente",
            previous: "Anterior",
        },
    },
    columnDefs: [
        { targets: 0, width: "10%" }, // ID
        { targets: 1, width: "30%" }, // Nombre del Producto
        { targets: 2, width: "15%" }, // Precio Unitario
        { targets: 3, width: "10%" }, // Cantidad
        { targets: 4, width: "15%" }, // Subtotal
        { targets: 5, orderable: false, width: "10%" }, // Opciones
    ],
});



    
});

function clearVenta() {
    // Limpiar los campos relacionados con la venta
    $("#total").val("0");
    $("#igv").val("0");
    $("#subtotal-price").val("0");
   
    contadorFilas = 0;
}



function deleteFilaProducto(id) {
    // Eliminar la fila correspondiente al ID del producto
    $("#tablaDetalleVenta").DataTable().rows().every(function () {
        if (this.data()[0] == id) {
            this.remove().draw(false);
        }
    });
    calcularYMostrarTotal();
}


$(document).ready(function () {
    $.fn.dataTable.ext.errMode = 'none';


    $("#tablaProductos").DataTable({
		"lengthMenu": [3, 4, 5],
        language: {
            lengthMenu: "Mostrar _MENU_ registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del _START_ al _END_ de _TOTAL_",
            infoEmpty: "Sin resultados",
            search: "Buscar: ",
            paginate: {
                first: "Primero",
                last: "Último",
                next: "Siguiente",
                previous: "Anterior",
            },
        },
        columnDefs: [
            { targets: 0, width: "10%" },
            { targets: 1, width: "30%" },
            { targets: 2, width: "15%" },
            { targets: 3, width: "20%" },
            { targets: 4, width: "10%" },
            { targets: 5, orderable: false, width: "15%" }
        ],
    });

  $(document).on('click', '.seleccionar', function () {
        let id = $($(this).parents('tr')[0].children[0]).text();
        setFilaProductoToDetalles(id);
    });

    $("#guardarVenta").click(function () {
       saveVenta()
    });

    $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Producto',
            text: "¿Está seguro de querer eliminar este Producto?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si'
        }).then((result) => {
            if (result.isConfirmed) {
                let id = $($(this).parents('tr')[0].children[0]).text();
                deleteFilaProducto(id);
            }
        });
    });



    getTablaProducto();
});

$.fn.dataTable.ext.errMode = 'none';

 

    // Función para buscar cliente por DNI

function calcularYMostrarTotal() {
    let total = 0;
    $("#tablaDetalleVenta").DataTable().rows().every(function () {
        // Obtener el valor del subtotal dentro del <span> con clase "subtotal"
        let subtotal = parseFloat($(this.node().querySelector(".subtotal")).text());
    
        total += subtotal;
    });


    
        let igv = total * 0.18; // Calcula el 18% del subtotal como IGV
    let montoTotal = total + igv; // Calcula el monto total sumando el IGV al subtotal

    $("#subtotal-price").val(total.toFixed(2));
    $("#igv").val(igv.toFixed(2));
    $("#total").val(montoTotal.toFixed(2));
}

