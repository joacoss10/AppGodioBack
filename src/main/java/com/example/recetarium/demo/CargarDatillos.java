package com.example.recetarium.demo;

import com.example.recetarium.demo.Model.CronogramaCurso;
import com.example.recetarium.demo.Model.Curso;
import com.example.recetarium.demo.Model.Enums.DiaDeSemana;
import com.example.recetarium.demo.Model.Enums.ModalidadClase;
import com.example.recetarium.demo.Model.Enums.TipoPromocion;
import com.example.recetarium.demo.Model.Sede;
import com.example.recetarium.demo.Repository.CronogramaCursoRepository;
import com.example.recetarium.demo.Repository.CursoRepository;
import com.example.recetarium.demo.Repository.SedeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CargarDatillos implements CommandLineRunner {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private CronogramaCursoRepository cronogramaCursoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (cursoRepository.count() == 0 && sedeRepository.count() == 0) {
            List<Sede> sedes = generarSedes();
            sedeRepository.saveAll(sedes);

            List<Curso> cursos = generarCursos(sedes);
            cursoRepository.saveAll(cursos);

            System.out.println("Cursos, sedes y cronogramas cargados automáticamente.");
        }
    }

    private List<Sede> generarSedes() {
        List<Sede> sedes = new ArrayList<>();
        String[] nombres = {"Sede Central", "Sucursal Norte", "Sucursal Oeste"};
        String[] direcciones = {"Calle Falsa 123", "Av. Siempre Viva 742", "Ruta 8 km 45"};
        String[] telefonos = {"1111-1111", "2222-2222", "3333-3333"};
        String[] mails = {"central@cocina.com", "norte@cocina.com", "oeste@cocina.com"};
        String[] whatsapps = {"11-1111-1111", "22-2222-2222", "33-3333-3333"};
        TipoPromocion[] promociones = TipoPromocion.values();

        Random random = new Random();
        for (int i = 0; i < nombres.length; i++) {
            Sede sede = new Sede();
            sede.setNombreSede(nombres[i]);
            sede.setDireccionSede(direcciones[i]);
            sede.setTelefonoSede(telefonos[i]);
            sede.setMailSede(mails[i]);
            sede.setWhatsApp(whatsapps[i]);
            sede.setBonificacionCurso(random.nextFloat() * 0.3f);
            sede.setTipoPromocion(promociones[random.nextInt(promociones.length)]);
            sedes.add(sede);
        }
        return sedes;
    }

    private List<Curso> generarCursos(List<Sede> sedes) {
        List<Curso> cursos = new ArrayList<>();
        Random random = new Random();

        String[] nombres = { "Panadería básica", "Pastelería creativa", "Cocina internacional",
                "Sushi para principiantes", "Panadería avanzada", "Cocina italiana",
                "Comida saludable", "Cocina vegana", "Asado argentino", "Cocina mexicana",
                "Repostería sin gluten", "Comida rápida saludable", "Cocina asiática",
                "Curso de empanadas", "Taller de tortas", "Cocina francesa",
                "Cocina española", "Pizzas y empanadas", "Comida peruana", "Cocina al wok"};

        String[] descripciones = { "Aprendé técnicas tradicionales y modernas.",
                "Curso práctico con recetas paso a paso.",
                "Ideal para principiantes y entusiastas.",
                "Descubrí sabores y secretos de chefs.",
                "Recetas auténticas y fáciles de preparar.",
                "Explorá platos clásicos y modernos.",
                "Aprendé a cocinar platos típicos.",
                "Técnicas para preparar comidas saludables.",
                "Sabores intensos y recetas novedosas.",
                "Cocina rápida y deliciosa para tu día a día." };

        String[][] contenidosEjemplo = {  {"Manejo de ingredientes", "Preparación básica", "Técnicas de cocción"},
                {"Uso de especias", "Recetas tradicionales", "Platos rápidos"},
                {"Postres y dulces", "Masa y levados", "Decoración básica"},
                {"Panadería avanzada", "Técnicas profesionales", "Recetas gourmet"},
                {"Comidas rápidas", "Salsas y acompañamientos", "Presentación de platos"} };

        String[][] requerimientosEjemplo = {  {"Utensilios básicos", "Ganas de aprender"},
                {"Conocimiento previo de cocina", "Disponibilidad horaria"},
                {"Interés en panadería", "Paciencia y dedicación"},
                {"Experiencia mínima", "Gusto por la repostería"},
                {"No se requieren requisitos"} };

        ModalidadClase[] modalidades = ModalidadClase.values();
        DiaDeSemana[] dias = DiaDeSemana.values();

        for (int i = 0; i < 20; i++) {
            Curso curso = new Curso();
            curso.setNombreCurso(nombres[i]);
            curso.setDescripcion(descripciones[random.nextInt(descripciones.length)]);
            curso.setContenidos(new ArrayList<>(List.of(contenidosEjemplo[random.nextInt(contenidosEjemplo.length)])));
            curso.setRequerimientos(new ArrayList<>(List.of(requerimientosEjemplo[random.nextInt(requerimientosEjemplo.length)])));
            curso.setDuracion(20 + random.nextInt(3));
            curso.setPrecio(800 + random.nextFloat() * 1700);
            curso.setModalidad(modalidades[random.nextInt(modalidades.length)]);
            int horaRandom = 8 + random.nextInt(10);
            curso.setHora(Time.valueOf(String.format("%02d:00:00", horaRandom)));


            Sede sedeAsignada = sedes.get(random.nextInt(sedes.size()));

            // Cronograma
            List<CronogramaCurso> cronogramas = new ArrayList<>();
            int cantidadCronogramas = 1 + random.nextInt(2);
            for (int j = 0; j < cantidadCronogramas; j++) {
                CronogramaCurso cronograma = new CronogramaCurso();
                cronograma.setCurso(curso);
                cronograma.setDia(dias[random.nextInt(dias.length)]);
                LocalDate inicio = LocalDate.now().plusDays(30+random.nextInt(50));
                cronograma.setFechaInicio(inicio);
                cronograma.setFechaFin(inicio.plusWeeks(4 + random.nextInt(4)));
                cronograma.setClasesDictadas(0);
                int vacantes=random.nextInt(40);
                cronograma.setVacantes(vacantes);
                cronograma.setSede(sedeAsignada);
                cronogramas.add(cronograma);
            }

            curso.setCronogramaCurso(cronogramas);
            cursos.add(curso);
        }

        return cursos;
    }
}



