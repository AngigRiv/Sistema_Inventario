const url = "/api/productos";
var metodo="";

let day = new Date().toLocaleDateString('es-ES', {
	day: '2-digit',
	month: '2-digit',
	year: 'numeric',
	hour: 'numeric',
	minute: 'numeric',
	second: 'numeric'
 }).replace(',', '').replace(/\//g, '-');
 
function save(bandera) {
    $("#modal-update").modal("hide");
    let id = $("#guardar").data("id");
    let producto = {
        id: id,
        nombre: $("#nombre").val(),
        descripcion: $("#descripcion").val(),
        stock: $("#stock").val(),
        precio: $("#precio").val(),
        _active: $("#is_active").is(":checked"),
        marca_id: {
            id: $("#marca").val() // Debes tener un elemento HTML con el id "marca" para seleccionar la marca del producto.
        },
        categoria_id: {
            id: $("#categoria").val() // Debes tener un elemento HTML con el id "categoria" para seleccionar la categoría del producto.
        },
        unidad_id: {
            id: $("#unidades").val() // Debes tener un elemento HTML con el id "unidadMedida" para seleccionar la unidad de medida del producto.
        }
    };
	
    let metodo = bandera == 1 ? "POST" : "PUT";
  $.ajax({
    type: metodo,
    url: url,
    data: JSON.stringify(producto),
    dataType: "text",
    contentType: "application/json",
    cache: false,
    success: function (data) {
        if (data == 0) {
            Swal.fire({
                icon: 'error',
                title: 'El producto ya está registrado',
                showConfirmButton: false,
                timer: 1500
            });
        } else {
            let texto = bandera == 1 ? "guardado" : "actualizado";
            getTabla();
            Swal.fire({
                icon: 'success',
                title: 'Se ha ' + texto + ' el producto',
                showConfirmButton: false,
                timer: 1500
            });
            clear();
        }
    },
    fail: function (jqXHR, textStatus, errorThrown) {
        console.error("Error en la petición AJAX:", textStatus, errorThrown);

        // Aquí puedes mostrar un mensaje de error al usuario o realizar otras acciones de manejo de errores.
        Swal.fire({
            icon: 'error',
            title: 'Error en la petición AJAX',
            text: 'Hubo un problema al guardar o actualizar el producto. Por favor, inténtalo de nuevo más tarde.',
            showConfirmButton: false,
            timer: 3000
        });
    }
});

}

function deleteFila(id) {
    $.ajax({
        type: "DELETE",
        url: url + "/" + id,
        data: {
            id: id,
        },
        cache: false,
        timeout: 600000,
        success: function (data) {
            Swal.fire({
                icon: 'success',
                title: 'Se ha eliminado el producto',
                showConfirmButton: false,
                timer: 1500
            });
            
            
            getTabla();
        },
    }).fail(function () {
        // Manejar error en caso de que falle la petición AJAX.
    });

}

function getTabla() {
    $.ajax({
        type: "GET",
        url: url,
        dataType: "text",
        cache: false,
        success: function (data) {

            let t = $("#tablaProductos").DataTable({
        language: {
            lengthMenu: "Mostrar MENU registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del START al END DE TOTAL",
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
            { targets: 1, width: "80%" },
            { targets: 2, orderable: false, width: "10%" }
        ],
    });
	
            t.clear().draw(false);

            let botonera = '<button type="button" class="btn btn-warning btn-sm editar"><i class="fas fa-edit"></i> </button>' +
                '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';

            let js = JSON.parse(data);
			
            $.each(js, function (a, producto) {

                t.row.add([
                    producto.id,
                    producto.nombre,
                    producto.descripcion,
                    producto.stock,
                    producto.precio,
                    producto._active ? 'Activo' : 'Inactivo',
                    producto.marca_id.nombre, // Suponiendo que el objeto "marca" en Producto tiene un atributo "nombreMarca".
                    producto.categoria_id.nombre, // Suponiendo que el objeto "categoria" en Producto tiene un atributo "nombreCategoria".
                    producto.unidad_id.nombre, // Suponiendo que el objeto "unidadMedida" en Producto tiene un atributo "nombreMedida".
                    botonera
                ]);
            });

            t.draw(false);

        },
    }).fail(function () {
        // Manejar error en caso de que falle la petición AJAX.
    });
}


 
function getFila(id) {
    $.ajax({
        type: "GET",
        url: url + "/" + id,
        cache: false,
        timeout: 600000,
        success: function (data) {
            $("#modal-title").text("Editar Producto");
            $("#nombre").val(data.nombre);
            $("#descripcion").val(data.descripcion);
            $("#stock").val(data.stock);
            $("#precio").val(data.precio);
            $("#is_active").prop("checked", data._active); // Use "prop" to set the "checked" property for a checkbox
            $("#marca").val(data.marca_id.id); // Assuming the "marca_id" field is an object with an "id" property
            $("#categoria").val(data.categoria_id.id); // Assuming the "categoria_id" field is an object with an "id" property
            $("#unidades").val(data.unidad_id.id); // Assuming the "unidadmedida_id" field is an object with an "id" property
            $("#guardar").data("id", data.id);
            $("#guardar").data("bandera", 0);
            $("#modal-update").modal("show");
        },
        error: function () {
            Swal.fire({
                icon: 'error',
                title: 'Error en la petición AJAX',
                text: 'Hubo un problema al obtener los datos del estado de habitación. Por favor, inténtalo de nuevo más tarde.',
                showConfirmButton: false,
                timer: 3000
            });
        }
    });
}


function clear() {
    $("#modal-title").text("Nuevo Producto");
    $("#nombre").val("");
    $("#descripcion").val("");
    $("#stock").val("");
    $("#precio").val("");
    $("#is_active").prop("checked", false);
    $("#marca").val(""); // Clear the selected value for the "marca" dropdown
    $("#categoria").val(""); // Clear the selected value for the "categoria" dropdown
    $("#unidades").val(""); // Clear the selected value for the "unidadMedida" dropdown
    $("#guardar").data("id", 0);
    $("#guardar").data("bandera", 1);
}


$(document).ready(function () {
 // Función para llenar un campo select con las opciones recibidas
    function llenarSelect(selectId, opciones) {
        var select = document.getElementById(selectId);
        select.innerHTML = '<option value="">Seleccione una opción...</option>';
        
        opciones.forEach(function (opcion) {
            var option = document.createElement("option");
            option.value = opcion.id;
            option.textContent   = opcion.nombre;
            select.appendChild(option);
        });
    }

    // Llamada AJAX para obtener las marcas
    $.ajax({
        type: "GET",
        url: "/api/marcas", // Reemplaza esta URL con la ruta correcta de tu API para obtener las marcas
        dataType: "json",
        success: function (data) {
            llenarSelect("marca", data); // Llena el campo select "marca" con las opciones recibidas
        },
        error: function () {
            console.error("Error al obtener las marcas");
        }
    });

    // Llamada AJAX para obtener las categorías
    $.ajax({
        type: "GET",
        url: "/api/categorias", // Reemplaza esta URL con la ruta correcta de tu API para obtener las categorías
        dataType: "json",
        success: function (data) {
            llenarSelect("categoria", data); // Llena el campo select "categoria" con las opciones recibidas
        },
        error: function () {
            console.error("Error al obtener las categorías");
        }
    });

    // Llamada AJAX para obtener las unidades de medida
    $.ajax({
        type: "GET",
        url: "/api/unidades", // Reemplaza esta URL con la ruta correcta de tu API para obtener las unidades de medida
        dataType: "json",
        success: function (data) {
            llenarSelect("unidades", data); // Llena el campo select "unidadMedida" con las opciones recibidas
        },
        error: function () {
            console.error("Error al obtener las unidades de medida");
        }
    });
    
    $("#tablaProductos").DataTable({
        language: {
            lengthMenu: "Mostrar MENU registros",
            zeroRecords: "No se encontraron coincidencias",
            info: "Mostrando del START al END DE TOTAL",
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
            { targets: 1, width: "80%" },
            { targets: 2, orderable: false, width: "10%" }
        ],
    });

    clear();

    $("#nuevo").click(function () {
        clear();
    });

    $("#guardar").click(function () {
        let bandera = $("#guardar").data("bandera");
        save(bandera);
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
                deleteFila(id);
            }
        });
    });

    $(document).on('click', '.editar', function () {
        let id = $($(this).parents('tr')[0].children[0]).text();
        
        getFila(id);
    });

    getTabla();
    getSelect();

});
 $.fn.dataTable.ext.errMode = 'none';