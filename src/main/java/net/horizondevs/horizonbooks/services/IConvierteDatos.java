package net.horizondevs.horizonbooks.services;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
