import React, { useEffect, useState } from "react";

export function Tabla() {

  const [equipos, setEquipos] = useState([]);

  useEffect(() => {
    obtenerDatos();
  }, []);

  const obtenerDatos = async () => {
    const data = await fetch("http://localhost:8081/equipo");
    const equiposJson = await data.json();
    console.log(equiposJson);
    setEquipos(equiposJson);
  };

  const rutaBaseImg = "http://localhost:8081/uploads/";

  const eliminarEquipo = async (id) => {
    console.log(id);
    const url = "http://localhost:8081/equipo/" + id;
    const response = await fetch(url, { method: "DELETE" });
    const responseJson = await response.json();
    console.log(responseJson);
    const nuevaLista = equipos.filter((item) => item.id !== id);
    setEquipos(nuevaLista);
  };

  return (
    <div className="mt-5">
      <table className="table table-bordered ">
        <thead>
          <tr>
            <th scope="col">Nombre</th>
            <th scope="col">Dirección</th>
            <th scope="col">Ciudad</th>
            <th scope="col">Categoria</th>
            <th scope="col">Telefono</th>
            <th scope="col">Acciones</th>
          </tr>
        </thead>
        <tbody>
          {equipos.map((item) => (
            <tr key={item.id}>
              {/* <td>
                <img
                  src={rutaBaseImg + item.escudo}
                  width="50px"
                  alt="escudo"
                />
              </td> */}
              <td>{item.nombre}</td>
              <td>{item.direccion}</td>
              <td>{item.ciudad}</td>
              <td>{item.categoria.nombre}</td>
              <td>{item.telefono}</td>
              <td>
                <button className="btn btn-outline-success">Editar</button>
                <button
                  className="btn btn-outline-danger"
                  onClick={() => eliminarEquipo(item.id)}
                >
                  Eliminar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
