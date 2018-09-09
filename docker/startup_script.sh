# a simple shell script to provision the automation environment and start the execution 

# Start the selenium server in the background 
nohup /opt/bin/entry_point.sh &
# create a temp test directory 
sudo mkdir test_run

cd test_run
# Clone the repo from github 
sudo git clone https://github.com/bharathkumar-gopalan/code-challenge-ui 

# switch to the base maven project directory 
cd code-challenge-ui/automation-code-challenge

# start the browser automation in chrome 
sudo mvn clean test -Dbrowser=chrome
