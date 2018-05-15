set :application, 'Vitro'
set :repo_url, 'https://github.com/sul-dlss-labs/Vitro.git'

# Default branch is :master
ask :branch, `git rev-parse --abbrev-ref HEAD`.chomp

# Default deploy_to directory is /var/www/my_app_name
set :deploy_to, "/opt/app/vitro/src/Vitro"

# Default value for :format is :airbrussh.
# set :format, :airbrussh

# You can configure the Airbrussh format using :format_options.
# These are the defaults.
# set :format_options, command_output: true, log_file: "log/capistrano.log", color: :auto, truncate: :auto

# Default value for :pty is false
# set :pty, true

# Default value for :linked_files is []
# append :linked_files, 'config/config.sh'

# Default value for linked_dirs is []
# append :linked_dirs, 'log'

# Default value for default_env is {}
# set :default_env, { path: "/opt/ruby/bin:$PATH" }

# Default value for keep_releases is 5
# set :keep_releases, 5

# update shared_configs before restarting app
#before 'deploy:restart', 'shared_configs:update'

# namespace :maven do
#   desc 'package'
#   task :package do
#     on roles(:app) do
#       execute "cd #{current_path} && /usr/local/maven/bin/mvn clean package"
#     end
#   end
# end
#
# after 'deploy:finished', 'maven:package'
