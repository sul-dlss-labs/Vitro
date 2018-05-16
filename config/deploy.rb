set :application, 'Vitro'
set :repo_url, 'https://github.com/sul-dlss-labs/Vitro.git'

# Default branch is :master
ask :branch, `git rev-parse --abbrev-ref HEAD`.chomp

# Default deploy_to directory is /var/www/my_app_name
set :deploy_to, '/opt/app/vitro'

# Default value for :linked_files is []
# append :linked_files, 'config/config.sh'

# Default value for linked_dirs is []
# append :linked_dirs, 'log'

# Default value for default_env is {}
# set :default_env, { path: "/opt/ruby/bin:$PATH" }

# Default value for keep_releases is 5
# set :keep_releases, 5

# update shared_configs before restarting app
# before 'deploy:restart', 'shared_configs:update'

namespace :maven do
  desc 'Install the Vitro project using Maven with the specified settings file'
  task :install do
    on roles(:app) do
      execute "cd #{current_path} \
      && /usr/local/maven/bin/mvn install -s rialto-vitro-settings.xml"
    end
  end
end

namespace :tomcat do
  desc 'Restarts Tomcat on the server'
  task :restart do
    on roles(:app) do
      execute 'sudo service tomcat restart'
    end
  end
end

after 'deploy:finished', 'maven:install'
after 'deploy:finished', 'tomcat:restart'
