import axios from "axios";
import React, { useState } from "react";
import * as FormData from "form-data";

export function Form() {
  const [file, setFile] = useState(null);

  var imagenFile = null;

  var [form, setForm] = useState({
    nombre: "",
    telefono: "",
    ciudad: "",
    idClub: "",
    direccion:"",
    idCuerpoTecnico: "",
    imagen: null,
  });

  const onChange = (e) => {
    console.log(e.target.value);
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const responser = (e) => {
    e.preventDefault();

    //inicio
    const data = new FormData();
    console.log(form.telefono);
    data.append("ciudad", form.ciudad);
    data.append("direccion", form.direccion);
    data.append("idClub", form.idClub);
    data.append("idCuerpoTecnico", form.idCuerpoTecnico);
    data.append("nombre", form.nombre);
    data.append("telefono", form.telefono);
    // data.append("imagen", "fhsjdhf");

    var config = {
      method: "post",
      url: "http://localhost:8081/equipo/save/1",
      headers: {
        "Content-Type":
          "multipart/form-data; boundary=AaB03x" +
          "--AaB03x" +
          "Content-Disposition: file" +
          "Content-Type: png" +
          "Content-Transfer-Encoding: binary" +
          "...data... " +
          "--AaB03x--",
        Accept: "application/json",
        type: "formData",
      },
      data: data,
    };

    axios(config)
      .then(function (response) {
        console.log(JSON.stringify(response.data));
      })
      .catch(function (error) {
        console.log(error);
      });

    //fin
    window.location.replace("/listar");
  };

  const onChange2 = (e) => {
    setFile({ ...file, [e.target.name]: e.target.files[0] });
    imagenFile = e.target.files[0];
  };

  // const responser = (e) => {
  //   e.preventDefault();

  //   console.log(form);

  //   axios
  //     .post(
  //       "http://localhost:8081/equipo/save/1",
  //       {
  //         data: {
  //           nombre: form.nombre,
  //           telefono: form.telefono,
  //           ciudad: form.ciudad,
  //           idClub: form.idClub,
  //           idCuerpoTecnico: form.idCuerpoTecnico,
  //           imagen: form.imagen,
  //         },
  //       },
  //       {
  //         headers: {
  //           "Content-Type":
  //             "multipart/form-data; boundary=AaB03x" +
  //             "--AaB03x" +
  //             "Content-Disposition: file" +
  //             "Content-Type: png" +
  //             "Content-Transfer-Encoding: binary" +
  //             "...data... " +
  //             "--AaB03x--",
  //           Accept: "application/json",
  //           type: "formData",
  //         },
  //       }
  //     )
  //     .then(function (response) {
  //       console.log(response);
  //       // return response;
  //     })
  //     .catch(function (error) {
  //       return error;
  //     });
  // };

  // const onChange2 = (e) => {
  //   setForm({ ...form, [e.target.name]: e.target.files[0] });
  // };

  // function onFileChange(e, file) {
  //   console.log("on-file-change");
  //   var file = file || e.target.files[0],
  //     pattern = /image-*/,
  //     reader = new FileReader();
  //   console.log(file.name);
  //   if (!file.type.match(pattern)) {
  //     alert("Formato inválido");
  //     return;
  //   }
  //   console.log(this);
  //   this.setState({ loaded: false });

  //   reader.onload = e => {
  //     this.setState({
  //       imageSrc: reader.result,
  //       loaded: true,
  //       imageName: file.name
  //     });
  //     console.log(reader.result);
  //   };
  //   reader.readAsDataURL(file);
  // }

  return (
    <div className="mt-5 row">
      <div className="col-md-8">
        <h2>Registrar Equipo</h2>
        <form
          className="row g-3 mt-4"
          encType="multipart/form-data"
          onSubmit={responser}
          method="POST"
        >
          <div className="mb-3 row">
            <label for="inputPassword" className="col-sm-2 col-form-label">
              Nombre
            </label>
            <div className="col-sm-5">
              <input
                type="text"
                name="nombre"
                onChange={onChange}
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
                onChange={onChange}
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
                onChange={onChange}
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
                onChange={onChange}
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
                onChange={onChange}
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
                onChange={onChange}
                className="form-control"
                id="inputPassword"
              />
            </div>
          </div>
          {/* <div className="mb-3 row">
            <label for="inputPassword" className="col-sm-2 col-form-label">
              Escudo
            </label>
            <div class="col-sm-5">
              <input
                className="form-control"
                type="file"
                name="imagen"
                onChange={onChange2}
                accept="image/*"
              />
            </div>
          </div> */}
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
        <img src="https://image.freepik.com/vector-gratis/formulario-registro_23-2147981070.jpg" width="500px"></img>
      </div>
    </div>
  );
}
