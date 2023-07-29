const urlVentas = "/api/ventas"; // Reemplaza esta URL con la ruta correcta de tu API para obtener las ventas

// Función para llenar la tabla de ventas
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
                title: 'Se ha eliminado la venta',
                showConfirmButton: false,
                timer: 1500
            });
            getTabla();
        },
    }).fail(function () {
        // Manejar error en caso de que falle la petición AJAX.
    });

}

let tablaClientes = null; 

function getTablaVentas() {
    $.ajax({
        type: "GET",
        url: "/api/ventas",
        dataType: "json",
        cache: false,
        success: function (data) {
            let t = $("#tablaVentas").DataTable({
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
                    { targets: 1, width: "20%" },
                    { targets: 2, width: "20%" },
                    { targets: 3, width: "15%" },
                    { targets: 4, width: "15%" },
                    { targets: 5, orderable: false, width: "10%" }
                ],
            });

            t.clear().draw(false);

            let botonera = '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';

            $.each(data, function (index, venta) {
                t.row.add([
                    venta.id,
                    new Date(venta.fecha).toLocaleDateString(),
                    (venta.total*0.18).toFixed(2),
                    (venta.total*0.18).toFixed(2),
                    venta.total.toFixed(2),
                    venta.estado,
                    "Sin acciones"
                ]);
            });

            t.draw(false);

        },
    }).fail(function () {
        // Manejar error en caso de que falle la petición AJAX.
    });
}


$(document).ready(function () {
  

    $(document).on('click', '.mostrar-detalles', function () {
          let id = $($(this).parents('tr')[0].children[0]).text();
        showDetalleVenta(id);
    });
       $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Venta',
            text: "¿Está seguro de querer eliminar esta venta?",
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

    getTablaVentas();
});
 $.fn.dataTable.ext.errMode = 'none';
 // ... Código existente ...

function showDetalleVenta(ventaId) {
	
$("#modal-update").modal("show");
    $.ajax({
        type: "GET",
        url:  "/api/detalleventas/"+ ventaId+ "/detalles", // URL para obtener los detalles de venta de una venta específica
        cache: false,
        timeout: 600000,
        success: function (data) {
			console.log(data)
            // Limpia la tabla antes de llenarla con los nuevos datos
            $("#tablaDetallesVenta").DataTable().clear().draw(false);

            // Llena la tabla con los detalles de venta recibidos
            let t = $("#tablaDetallesVenta").DataTable();
            $.each(data, function (index, detalle) {
				console.log(detalle)
				console.log("data",data)
				
				
                t.row.add([
                    detalle.id,
                    detalle.producto_id.nombre,
                    detalle.cantidad,
                    detalle.producto_id.precio,
                    detalle.total,
                ]);
               
            });

            t.draw(false);

            // Abre el modal para mostrar los detalles de venta
            $("#modal-title").text("Detalles de Venta - ID: " + ventaId);
            
        },
    }).fail(function () {
        // Manejar error en caso de que falle la petición AJAX.
    });
}


