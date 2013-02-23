/*
 * Copyright 2010, 2011, 2012 mapsforge.org
 *
 * This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.mapsforge.map.layer.cache;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.mapsforge.core.graphics.Bitmap;
import org.mapsforge.core.graphics.Canvas;
import org.mapsforge.core.graphics.GraphicFactory;
import org.mapsforge.core.graphics.Matrix;
import org.mapsforge.core.graphics.Paint;

public final class DummyGraphicFactory implements GraphicFactory {
	public static final DummyGraphicFactory INSTANCE = new DummyGraphicFactory();

	private DummyGraphicFactory() {
		// do nothing
	}

	@Override
	public Bitmap createBitmap(InputStream inputStream) {
		try {
			BufferedImage bufferedImage = ImageIO.read(inputStream);
			byte[] imageBytes = ((DataBufferByte) bufferedImage.getData().getDataBuffer()).getData();
			Bitmap bitmap = INSTANCE.createBitmap(bufferedImage.getWidth(), bufferedImage.getHeight());
			bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(imageBytes));
			return bitmap;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public Bitmap createBitmap(int width, int height) {
		return new DummyBitmap(width, height);
	}

	@Override
	public Canvas createCanvas() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Matrix createMatrix() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Paint createPaint() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getColor(Color color) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int parseColor(String colorString) {
		throw new UnsupportedOperationException();
	}
}