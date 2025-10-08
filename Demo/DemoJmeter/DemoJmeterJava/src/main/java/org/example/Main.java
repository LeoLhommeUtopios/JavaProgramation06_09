package org.example;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.report.config.ConfigurationException;
import org.apache.jmeter.report.dashboard.GenerationException;
import org.apache.jmeter.report.dashboard.ReportGenerator;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jorphan.collections.ListedHashTree;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, GenerationException, ConfigurationException {

                // 1️⃣ Initialisation du moteur JMeter
                StandardJMeterEngine jmeter = new StandardJMeterEngine();

                // Chemin vers ton installation JMeter
                JMeterUtils.setJMeterHome("C:\\Users\\utopios\\Desktop\\apache-jmeter-5.6.3");
                JMeterUtils.loadJMeterProperties("C:\\Users\\utopios\\Desktop\\apache-jmeter-5.6.3/bin/jmeter.properties");
                JMeterUtils.initLocale();

                // 2️⃣ Contrôleur de boucle
                LoopController loopController = new LoopController();
                loopController.setLoops(1);
                loopController.setFirst(true);
                loopController.initialize();

                // 3️⃣ Création du ThreadGroup via les setters publics
                ThreadGroup threadGroup = new ThreadGroup();
                threadGroup.setName("TestGroup - API /comment");
                threadGroup.setNumThreads(5); // 5 utilisateurs virtuels
                threadGroup.setRampUp(2);     // montée en charge sur 2 secondes
                threadGroup.setSamplerController(loopController);
                threadGroup.setProperty(TestElement.TEST_CLASS, ThreadGroup.class.getName());
                threadGroup.setProperty(TestElement.GUI_CLASS, "ThreadGroupGui");

                // 4️⃣ Création d'une requête HTTP (POST)
                HTTPSamplerProxy httpSampler = new HTTPSamplerProxy();
                httpSampler.setDomain("localhost");
                httpSampler.setPort(8084);
                httpSampler.setPath("/api/comment/create");
                httpSampler.setMethod("POST");
                httpSampler.setName("POST - /api/comment");
                httpSampler.setPostBodyRaw(true);

                // Corps JSON de la requête
                String jsonBody = "{\"author\":\"Jean\",\"message\":\"Super article!\"}";
                httpSampler.addNonEncodedArgument("", jsonBody, "");
                httpSampler.setProperty(TestElement.TEST_CLASS, HTTPSamplerProxy.class.getName());
                httpSampler.setProperty(TestElement.GUI_CLASS, "HttpTestSampleGui");

                // 5️⃣ En-têtes HTTP
                HeaderManager headerManager = new HeaderManager();
                headerManager.add(new Header("Content-Type", "application/json"));
                headerManager.add(new Header("Accept", "application/json"));
                headerManager.setName("HTTP Header Manager");
                headerManager.setProperty(TestElement.TEST_CLASS, HeaderManager.class.getName());
                headerManager.setProperty(TestElement.GUI_CLASS, "HeaderPanel");

                // 6️⃣ Plan de test
                TestPlan testPlan = new TestPlan("Test Plan - API Comment");
                testPlan.setProperty(TestElement.TEST_CLASS, TestPlan.class.getName());
                testPlan.setProperty(TestElement.GUI_CLASS, "TestPlanGui");
                testPlan.setUserDefinedVariables(new Arguments());

                // 7️⃣ Construction de l’arbre de test
                ListedHashTree testPlanTree = new ListedHashTree();
                ListedHashTree testPlanHashTree = testPlanTree.add(testPlan);
                ListedHashTree threadGroupHashTree = testPlanHashTree.add(threadGroup);
                threadGroupHashTree.add(httpSampler);
                threadGroupHashTree.add(headerManager);

                // 8️⃣ Sauvegarde optionnelle du plan en .jmx
                SaveService.saveTree(testPlanTree, new FileOutputStream("api-comment-test.jmx"));

                // 9️⃣ Résumé des résultats
                Summariser summariser = new Summariser("summary");

                ReportGenerator generator = new ReportGenerator("C:\\Users\\utopios\\Desktop\\apache-jmeter-5.6.3\\resultats.jtl",null);
                generator.generate();
                System.out.println("Rapport HTML généré dans le dossier : " + "C:\\Users\\utopios\\Desktop\\apache-jmeter-5.6.3");

                // 🔟 Lancement du test
                jmeter.configure(testPlanTree);
                jmeter.run();

                System.out.println("\n✅ Test terminé !");
                System.out.println("➡️ 5 utilisateurs ont envoyé un POST vers http://localhost:8084/api/comment");
            }
        }
