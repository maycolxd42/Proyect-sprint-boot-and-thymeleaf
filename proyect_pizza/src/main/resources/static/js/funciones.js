// Cambia el cursor al hacer clic
document.addEventListener('click', function() {
    document.body.classList.add('active-cursor');

    // Espera un segundo y luego elimina la clase
    setTimeout(function() {
        document.body.classList.remove('active-cursor');
    }, 120);
});


//Función para minimizar el menú
function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "navbar") {
        x.className += " responsive";
    } else {
        x.className = "navbar";
    }
}


