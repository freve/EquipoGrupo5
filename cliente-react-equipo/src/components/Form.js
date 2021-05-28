import React from "react";
import * as FormData from "form-data";

export function Form() {

  const enviarForm = (e) => {

    e.preventDefault();
    var dataForm = new FormData(document.forms.namedItem("formulario"));

    var req = new XMLHttpRequest();
    req.open("POST", "http://localhost:8081/equipo/save/1", true);

    req.onload = function(oEvent) {
      if (req.status === 201) {
window.location.replace("/listar");
        console.log(req.response);
      } else {
        console.log("Status:" +req.status + "Error : "+req.response);
      }
    };
    req.send(dataForm);

  };

  return (
    <div className="mt-5 row">
      <div className="col-md-8">
        <h2>Registrar Equipo</h2>
        <form
          className="row g-3 mt-4"
          encType="multipart/form-data"
          onSubmit={enviarForm}
          method="POST"
          name="formulario"
        >
          <div className="mb-3 row">
            <label for="inputPassword" className="col-sm-2 col-form-label">
              Nombre
            </label>
            <div className="col-sm-5">
              <input
                type="text"
                name="nombre"
                className="form-control"
                id="inputPassword"
              />
            </div>
          </div>
          <div className="mb-3 row">
            <label for="inputPassword" className="col-sm-2 col-form-label">
              Telefono
            </label>
            <div className="col-sm-5">
              <input
                type="number"
                name="telefono"
                className="form-control"
              />
            </div>
          </div>
          <div className="mb-3 row">
            <label for="inputPassword" className="col-sm-2 col-form-label">
              Dirección
            </label>
            <div className="col-sm-5">
              <input
                type="text"
                name="direccion"
                className="form-control"
                id="inputPassword"
              />
            </div>
          </div>
          <div className="mb-3 row">
            <label for="inputPassword" className="col-sm-2 col-form-label">
              Ciudad
            </label>
            <div className="col-sm-5">
              <input
                type="text"
                name="ciudad"
                className="form-control"
                id="inputPassword"
              />
            </div>
          </div>
          <div className="mb-3 row">
            <label for="inputPassword" className="col-sm-2 col-form-label">
              Cuerpo Técnico
            </label>
            <div className="col-sm-5">
              <input
                type="text"
                name="idCuerpoTecnico"
                className="form-control"
                id="inputPassword"
              />
            </div>
          </div>
          <div className="mb-3 row">
            <label for="inputPassword" className="col-sm-2 col-form-label">
              Club
            </label>
            <div className="col-sm-5">
              <input
                type="text"
                name="idClub"
                className="form-control"
                id="inputPassword"
              />
            </div>
          </div>
          <div className="mb-3 row">
            <label for="inputPassword" className="col-sm-2 col-form-label">
              Escudo
            </label>
            <div class="col-sm-5">
              <input
                className="form-control"
                type="file"
                name="imagen"

                accept="image/*"
              />
            </div>
          </div>
          <div className="mb-3 row justify-content-center">
            <div className="col-auto">
              <button type="submit" className="btn btn-primary mb-3">
                Guardar Equipo
              </button>
            </div>
          </div>
        </form>
      </div>
      <div className="col-md-4">
        <img src="https://image.freepik.com/vector-gratis/formulario-registro_23-2147981070.jpg" width="500px" alt="img"></img>
      </div>
    </div>
  );
}
