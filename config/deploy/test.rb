server 'rialto-vitro-test', user: 'vitro', roles: 'app'

# allow ssh to host
Capistrano::OneTimeKey.generate_one_time_key!
