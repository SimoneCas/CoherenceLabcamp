package it.reply.labcamp.coherence.client;

/*
 * Implementare l'interfaccia MapListener di coherence, loggare tutti gli eventi catturati specificando se l'evento è una Delete/Insert/Update
 * https://docs.oracle.com/middleware/12213/coherence/develop-applications/using-map-events.htm#COHDG5187
 */
public class CustomerListener  {

	private int insertEventsCounter = 0;
	private int deleteEventsCounter = 0;
	private int updateEventsCounter = 0;
	
	

	public int getInsertEventsCounter() {
		return insertEventsCounter;
	}

	public int getDeleteEventsCounter() {
		return deleteEventsCounter;
	}

	public int getUpdateEventsCounter() {
		return updateEventsCounter;
	}

	@Override
	public int hashCode() {
		return this.getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass().equals(obj.getClass()))
			return true;
		return false;
	}

}
