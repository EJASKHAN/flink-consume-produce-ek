# flink-consume-produce-ek
This FLINK project will consume from an azure event-hub and produce to a different event-hub ,and the code for deploying the same in kubernetes

#Getting Started with IDE

Steps for running in IDE like Intellij,

1. Clone this repo.
2. From Intellij, navigate File->New->Project-> <select the already cloned project>
3. Edit the EH name in FlinkTestConsumerProducer.java and broker name & connection string in consumer.config (if required)
4. From the Maven tool window, perform the lifecycle - clean and compile.
5. Run the main class <FlinkTestConsumerProducer>.
6. Clone the project flink-producer-ek and run the same. 

#Docker image
Once you commit the code changes , an image will get generated in ACR.
#Kubernetes Deployment


Steps for running in Kubernetes(Manual steps),

1. Login to your AKS cluster using any command line tools, eg: AzureCLI or AzureCloudShell.
   OR use minikube for local testing.
2. Clone/Navigate to the project directory.
3. CD Kubernetes
4. Create the resources in the following order.

    a. kubectl create -f namespace.yaml <For creating a dedicated namespace in the cluster >
    b. kubectl create -f dockersecret.yaml <For creating the secret for pulling image from azure container registry>
    c. kubectl create -f flink-configuration-configmap.yaml <For creating the ConfigMap for all the configuration required for flink eco-system>
    d. kubectl create  -f jobmanager-service.yaml <For creating the job Manager Service>
    e. kubectl create -f jobmanager-deployment.yaml <For creating the job Manager deployment>
    f. kubectl create  -f taskmanager-deployment.yaml <For creating the taskmanager and registering it with job manager>


We could manage the above mentioned kubernetes resources and automate the deployment by using either one of the following ways in future
1.Helm  Charts.
2.Kustomize.
3.Kubernetes Operator.
