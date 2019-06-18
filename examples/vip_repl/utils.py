db = {}


def create_context(name="default"):
    global db
    """[summary]

  Keyword Arguments:
      name {str} -- [description] (default: {"default"})
  """
    if db[name]:
        raise(Exception("context name already exist"))
    db[name] = {}
    return db[name]


def default_context():
    global db
    context = db.get("default")
    if context:
        return context
    else:
        return create_context()


def start_ui_server():
    import subprocess
    subprocess.run(["cd", "../client", "&&", "npm", "run", "dev"])

def start_data_server(context):
    from someware import start_ws_server
    if not context:
        context = default_context()
    start_ws_server(context)


def register_event(event_id, client_callback, server_callback):
  """registers an event on 
  
  Arguments:
      event_id {str} -- ui event such as click change etc
      client_callback {js function} -- code to implement on client side when such event happens
      server_callback {function} -- callback function on data for the server
  Returns:
      Boolean -- Return True is the registration was successful
  """
  return True

