const urlCompras = "/api/compra";

// Función para llenar la tabla de compras
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
                title: 'Se ha eliminado la compra',
                showConfirmButton: false,
                timer: 1500
            });
            getTabla();
        },
    }).fail(function () {
        
    });

}

let tablaClientes = null; 

function getTablaCompras() {
    $.ajax({
        type: "GET",
        url: "/api/compras",
        dataType: "json",
        cache: false,
        success: function (data) {
			console.log(data)
            let t = $("#tablaCompras").DataTable({
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
                    { targets: 5, width: "15%" },
                    { targets: 6, orderable: false, width: "10%" }
                ],
            });

            t.clear().draw(false);

            let botonera = '<button type="button" class="btn btn-danger btn-sm eliminar"><i class="fas fa-trash"></i></button>';

            $.each(data, function (index, compra) {
				console.log(compra)
                t.row.add([
                    compra.id,
                    compra.proveedor.nombre,
                    new Date(compra.fecha).toLocaleDateString(),
                    (compra.total*0.18).toFixed(2),
                    (compra.total*0.18).toFixed(2),
                    compra.total.toFixed(2),
                    compra.estado,
                    "Sin acciones"
                ]);
            });

            t.draw(false);

        },
    }).fail(function () {
        
    });
}


$(document).ready(function () {
  

    
    $(document).on('click', '.mostrar-detalles', function () {
          let id = $($(this).parents('tr')[0].children[0]).text();
        showDetalleCompra(id);
    });
       $(document).on('click', '.eliminar', function () {
        Swal.fire({
            title: 'Eliminar Compra',
            text: "¿Está seguro de querer eliminar esta compra?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Si'
        }).then((result) => {
            if (result.isConfirmed) {
                let id = $($(this).parents('tr')[0].children[0]).text();
                console.log(id)
                deleteFila(id);
            }
        });
    });

    getTablaCompras();
});
 $.fn.dataTable.ext.errMode = 'none';
 // ... Código existente ...

function showDetalleCompra(compraId) {
	
$("#modal-update").modal("show");
    $.ajax({
        type: "GET",
        url:  "/api/detallecompras/"+ compraId+ "/detalles", // URL para obtener los detalles de compra de una compra específica
        cache: false,
        timeout: 600000,
        success: function (data) {
			console.log(data)
            // Limpia la tabla antes de llenarla con los nuevos datos
            $("#tablaDetallesCompra").DataTable().clear().draw(false);

            // Llena la tabla con los detalles de compra recibidos
            let t = $("#tablaDetallesCompra").DataTable();
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

            // Abre el modal para mostrar los detalles de compra
            $("#modal-title").text("Detalles de Compra - ID: " + compraId);
            
        },
    }).fail(function () {
        // Manejar error en caso de que falle la petición AJAX.
    });
}


