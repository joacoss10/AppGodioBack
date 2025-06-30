package com.example.recetarium.demo;

import com.example.recetarium.demo.Model.*;
import com.example.recetarium.demo.Model.Enums.DiaDeSemana;
import com.example.recetarium.demo.Model.Enums.ModalidadClase;
import com.example.recetarium.demo.Model.Enums.TipoPromocion;
import com.example.recetarium.demo.Repository.*;
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
    private UnidadRepository unidadRepository;
    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private CronogramaCursoRepository cronogramaCursoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (unidadRepository.count() == 0) {
            cargarUnidadesYConversiones();
        }
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
    private void cargarUnidadesYConversiones() {
        String[] descripciones = {"gramos", "kilogramos", "mililitros", "litros", "unidad",
                "cucharadas", "cucharaditas", "tazas", "pizca"};
        List<Unidad> unidades = new ArrayList<>();
        for (String desc : descripciones) {
            Unidad u = new Unidad();
            u.setDescripcion(desc);
            unidades.add(u);
        }
        unidadRepository.saveAll(unidades);

        List<Conversion> conversiones = new ArrayList<>();
        for (Unidad u : unidades) {
            Conversion c = new Conversion();
            c.setUnidadOrigen(u);
            c.setUnidadDestino(u);
            c.setFactorConversiones(1f);
            conversiones.add(c);
        }

        Unidad gramos = unidades.get(0);
        Unidad kilogramos = unidades.get(1);
        Unidad mililitros = unidades.get(2);
        Unidad litros = unidades.get(3);

        conversiones.add(crearConversion(gramos, kilogramos, 0.001f));
        conversiones.add(crearConversion(kilogramos, gramos, 1000f));
        conversiones.add(crearConversion(mililitros, litros, 0.001f));
        conversiones.add(crearConversion(litros, mililitros, 1000f));

        conversionRepository.saveAll(conversiones);
    }

    private Conversion crearConversion(Unidad origen, Unidad destino, float factor) {
        Conversion c = new Conversion();
        c.setUnidadOrigen(origen);
        c.setUnidadDestino(destino);
        c.setFactorConversiones(factor);
        return c;
    }


    private List<Curso> generarCursos(List<Sede> sedes) {
        List<Curso> cursos = new ArrayList<>();
        Random random = new Random();

        String[] nombres = {
                "Panadería básica", "Pastelería creativa", "Cocina internacional",
                "Sushi para principiantes", "Panadería avanzada", "Cocina italiana",
                "Comida saludable", "Cocina vegana", "Asado argentino", "Cocina mexicana",
                "Repostería sin gluten", "Comida rápida saludable", "Cocina asiática",
                "Curso de empanadas", "Taller de tortas", "Cocina francesa",
                "Cocina española", "Pizzas y empanadas", "Comida peruana", "Cocina al wok"
        };

        String[] descripciones = {
                "Este curso ofrece una introducción completa a las técnicas tradicionales y modernas de la cocina, abarcando desde la selección de ingredientes hasta la presentación final de los platos. Ideal tanto para aficionados como para futuros profesionales.",
                "Aprendé a crear postres impactantes visual y gustativamente, con técnicas de repostería profesional y herramientas modernas. Desde cupcakes decorados hasta tortas temáticas complejas.",
                "Explorá sabores del mundo cocinando platos típicos de Asia, Europa y América Latina. Conocerás ingredientes exóticos y métodos autóctonos de preparación culinaria.",
                "Dominá las bases del sushi: tipos de arroz, cortes de pescado, presentación de rolls y nigiris. Incluye prácticas con ingredientes frescos y consejos de manipulación segura.",
                "Curso avanzado para panaderos con experiencia. Amasados de alta hidratación, fermentaciones largas, masa madre y hornos profesionales. Incluye teoría y práctica intensiva.",
                "Curso completo sobre cocina italiana auténtica. Elaboración de pastas frescas, salsas regionales, risottos, antipastos y postres típicos como el tiramisú.",
                "Comé rico y sano. Recetas equilibradas, sin procesados, con alto valor nutricional y bajo en grasas. Incluye planificación semanal de menús y técnicas de cocción saludable.",
                "Convertí tus hábitos: cocina sin productos animales, aprovechando vegetales, legumbres y cereales. Preparaciones coloridas, sabrosas y éticas.",
                "El clásico asado argentino con un enfoque profesional. Cortes, tiempos de cocción, tipos de brasas, marinados, acompañamientos y trucos de parrillero.",
                "Descubrí los secretos de la cocina mexicana: tacos, enchiladas, salsas picantes y postres típicos. Aprendé sobre el uso del maíz, chiles y especias locales."
        };

        String[][] contenidosEjemplo = {
                {"Introducción a la cocina", "Manipulación segura de alimentos", "Cortes básicos", "Métodos de cocción", "Emplatado profesional", "Uso del horno"},
                {"Preparación de masas", "Fermentación y levado", "Técnicas de glaseado", "Decoración con manga", "Uso de moldes especiales", "Repostería artística"},
                {"Cocina europea clásica", "Platos típicos asiáticos", "Salsas internacionales", "Condimentos y especias globales", "Fusión de sabores", "Presentación cultural"},
                {"Preparación del arroz de sushi", "Selección de pescado fresco", "Técnicas de enrollado", "Presentación y decoración", "Utensilios japoneses", "Historia del sushi"},
                {"Masa madre desde cero", "Amasado francés y italiano", "Panificados integrales", "Pan de campo y ciabatta", "Uso de harinas especiales", "Panificación sin gluten"}
        };

        String[][] requerimientosEjemplo = {
                {"Tener cuchillos afilados", "Delantal y cofia", "Conocimiento básico de cocina", "Disponibilidad horaria", "Capacidad para trabajar en equipo", "Pasión por la gastronomía"},
                {"Experiencia previa en cocina", "Contar con batidora eléctrica", "Interés en repostería creativa", "Materiales de decoración propios", "Paciencia y precisión"},
                {"Haber cursado nivel básico de cocina", "Gusto por lo exótico", "Capacidad de atención a los detalles", "Asistencia a todas las clases teóricas y prácticas"},
                {"Gusto por la cultura japonesa", "Capacidad de manipular pescado crudo", "Manejo básico de cuchillos afilados", "Conocimientos mínimos en higiene alimentaria"},
                {"Disposición para trabajar largas horas de pie", "Interés en procesos fermentativos", "Capacidad de organización", "Asistencia completa obligatoria"}
        };

        ModalidadClase[] modalidades = ModalidadClase.values();
        DiaDeSemana[] dias = DiaDeSemana.values();

        for (int i = 0; i < 20; i++) {
            Curso curso = new Curso();
            curso.setNombreCurso(nombres[i]);
            curso.setDescripcion(descripciones[random.nextInt(descripciones.length)]);

            List<String> contenidos = new ArrayList<>();
            for (String[] grupo : contenidosEjemplo) {
                for (String c : grupo) {
                    if (random.nextBoolean()) contenidos.add(c);
                }
            }
            curso.setContenidos(contenidos);

            List<String> requerimientos = new ArrayList<>();
            for (String[] grupo : requerimientosEjemplo) {
                for (String r : grupo) {
                    if (random.nextBoolean()) requerimientos.add(r);
                }
            }
            curso.setRequerimientos(requerimientos);

            curso.setDuracion(20 + random.nextInt(3));
            curso.setPrecio(800 + random.nextFloat() * 1700);
            curso.setModalidad(modalidades[random.nextInt(modalidades.length)]);
            int horaRandom = 8 + random.nextInt(10);
            curso.setHora(Time.valueOf(String.format("%02d:00:00", horaRandom)));

            Sede sedeAsignada = sedes.get(random.nextInt(sedes.size()));

            List<CronogramaCurso> cronogramas = new ArrayList<>();
            int cantidadCronogramas = 1 + random.nextInt(2);
            for (int j = 0; j < cantidadCronogramas; j++) {
                CronogramaCurso cronograma = new CronogramaCurso();
                cronograma.setCurso(curso);
                cronograma.setDia(dias[random.nextInt(dias.length)]);
                LocalDate inicio = LocalDate.now().plusDays(30 + random.nextInt(50));
                cronograma.setFechaInicio(inicio);
                cronograma.setFechaFin(inicio.plusWeeks(4 + random.nextInt(4)));
                cronograma.setClasesDictadas(0);
                int vacantes = 10 + random.nextInt(30);
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



